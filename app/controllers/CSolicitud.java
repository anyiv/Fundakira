package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Beneficiario;
import models.DetalleSolicitud;
import models.Usuario_Beneficiario;
import models.Usuario;
import models.TipoUser;
import models.Servicio;
import models.Empleado;
import models.Fundacion;
import io.ebean.*;
import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Singleton
public class CSolicitud extends Controller{

    // CONSTRUCCION DE FORMULARIOS
    private Form<Solicitud> solicitudForm;
    private Form<Beneficiario> beneficiarioForm;
    private Form<Usuario> usuarioForm;

    @Inject
    public CSolicitud(FormFactory formFactory) {
        this.solicitudForm = formFactory.form(Solicitud.class);
        this.beneficiarioForm = formFactory.form(Beneficiario.class);
        this.usuarioForm = formFactory.form(Usuario.class);
    }

    public Result inicio_empleado() {
        return ok(views.html.inicio_empleado.render(solicitudForm,beneficiarioForm,usuarioForm,Servicio.buscador.listado()));
    }

    public Result solicitudes() {
        return ok(views.html.solicitudes.render(Solicitud.buscador.listadoTodos()));
    }

    public Result consulta_solicitud(String cod) {
        UUID codigo = UUID.fromString(cod);
        return ok(views.html.consultar_solicitud.render(Solicitud.buscador.porCodigo(codigo),DetalleSolicitud.buscador.listadoDet(codigo)));
    }

    //VALIDAR BENEFICIARIO AJAX
    @BodyParser.Of(BodyParser.Json.class)
    public Result validar_beneficiario(Http.Request request) {
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        String cedulab = json.findPath("cedulab").textValue();
        Beneficiario ben = Beneficiario.buscador.porCedula(cedulab);
        if (ben == null){
            respuesta.put("error",true);
            return ok(respuesta);
        }else{
            respuesta.put("nombre",ben.getNombreB());
            respuesta.put("apellido",ben.getApellidoB());
            respuesta.put("direccion",ben.getDireccionB());
            respuesta.put("telefono",ben.getTelefonoB());
            respuesta.put("correo",ben.getCorreoB());
            return ok(respuesta);
        }
    }

    //CREAR SOLICITUD AJAX
    @BodyParser.Of(BodyParser.Json.class)
    public Result crear_solicitud(Http.Request request) {
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        String cedulab = json.findPath("cedulab").textValue();
        List<Servicio> servicios = new ArrayList<Servicio>();
        for (JsonNode codservicio : json.withArray("servicios")) {
            servicios.add(Servicio.buscador.porCodigo(UUID.fromString(codservicio.textValue())));
        }
        String cedulae = request.session().getOptional("user").get();
        String pri = json.findPath("prioridad").textValue();
        String otradonac = json.findPath("otdonac").textValue();
        String razon = json.findPath("razon").textValue();
        Beneficiario ben = Beneficiario.buscador.porCedula(cedulab);
        Empleado emp = Empleado.buscador.porCedula(cedulae);
        Date fechaRegistro = new Date();
        Solicitud soli = new Solicitud(
            UUID.randomUUID(),
            emp,
            ben,
            otradonac,
            razon,
            pri,
            fechaRegistro,
            'P'
        );
        try {
            Ebean.save(soli);
            Fundacion f = soli.getEmpleado().getFundacion();
            double presupuesto = 0;
            for (Servicio servicio : servicios) {
                DetalleSolicitud ds = new DetalleSolicitud(soli, servicio, servicio.getCosto());
                Ebean.save(ds);
                presupuesto += ds.getCosto();
            }
            if (f.getMontoDisponible()-presupuesto<0){
                respuesta.put("resultado","La solicitud ha sido rechazada automáticamente por falta de presupuesto.");
                soli.setEstatus('N');
                soli.setRazon("Falta de presupuesto.");
                Ebean.update(soli);
            } else {
                respuesta.put("resultado","La solicitud se ha creado con éxito.");
            }
            return ok(respuesta);
        } catch (Exception e) {
            respuesta.put("resultado","Hubo un error creando la solicitud.");
            return ok(respuesta);
        }
    }

    //CREAR BENEFICIARIO
    public Result guardarB(){
        Form<Beneficiario> boundFormB = beneficiarioForm.bindFromRequest();
        Form<Usuario> boundFormUser = usuarioForm.bindFromRequest();
        if (boundFormB.hasErrors() || boundFormUser.hasErrors() ) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.inicio_empleado.render(solicitudForm,boundFormB,boundFormUser,Servicio.buscador.listado()));
        }
        Beneficiario beneficiario = boundFormB.get();
        Usuario usuario = boundFormUser.get();
        Usuario_Beneficiario ub = new Usuario_Beneficiario();
        TipoUser tp = TipoUser.buscador.porCodigo("3");
        ub.setUsuario(usuario);
        ub.setBeneficiario(beneficiario);
        usuario.setTipouser(tp);
        Ebean.save(beneficiario);
        Ebean.save(usuario);
        Ebean.save(ub);
        flash("success",String.format("El beneficiario %s %s ha sido incluido con éxito.", beneficiario.getNombreB(), beneficiario.getApellidoB()));        
        return redirect(routes.CSolicitud.inicio_empleado());
    }

    //GESTIONAR SOLICITUD(APROBAR O NEGAR)
    public Result aprobarSolicitud(String cod){
        UUID codigo = UUID.fromString(cod);
        final Solicitud solicitud = Solicitud.buscador.porCodigo(codigo);
        solicitud.setEstatus('A');
        Ebean.update(solicitud);
        flash("success",String.format("La solicitud ha sido aprobada con éxito."));
        return redirect(routes.CSolicitud.solicitudes());
    }

    public Result rechazarSolicitud(String cod){
        UUID codigo = UUID.fromString(cod);
        final Solicitud solicitud = Solicitud.buscador.porCodigo(codigo);
        solicitud.setEstatus('N');
        Ebean.update(solicitud);
        flash("success",String.format("La solicitud ha sido negada con éxito."));
        return redirect(routes.CSolicitud.solicitudes());
    }
}
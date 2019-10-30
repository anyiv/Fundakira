package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Beneficiario;
import models.Usuario_Beneficiario;
import models.Usuario;
import models.TipoUser;
import models.Servicio;
import io.ebean.*;
import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
        return ok(views.html.solicitudes.render());
    }

    public Result consulta_solicitud() {
        return ok(views.html.consultar_solicitud.render());
    }
    //VALIDAR BENEFICIARIO
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

    //CREAR SOLICITUD
    public Result guardarS(){
        Form<Solicitud> boundFormS = solicitudForm.bindFromRequest();
        if (boundFormS.hasErrors() ) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.inicio_empleado.render(boundFormS,beneficiarioForm,usuarioForm,Servicio.buscador.listado()));
        }
        Solicitud solicitud = boundFormS.get();

        return redirect(routes.CSolicitud.inicio_empleado());
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

}
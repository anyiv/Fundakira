package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Beneficiario;
import io.ebean.*;
import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class CSolicitud extends Controller{

    // CONSTRUCCION DE FORMULARIOS
    private Form<Solicitud> solicitudForm;
    private Form<Beneficiario> beneficiarioForm;

    @Inject
    public CSolicitud(FormFactory formFactory) {
        this.solicitudForm = formFactory.form(Solicitud.class);
        this.beneficiarioForm = formFactory.form(Beneficiario.class);
    }

    public Result inicio_empleado() {
        return ok(views.html.inicio_empleado.render(solicitudForm,beneficiarioForm));
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
        System.out.println("x");
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
        Form<Solicitud> boundForm = solicitudForm.bindFromRequest();
        String mensaje;
        System.out.print(boundForm);
        return redirect(routes.CSolicitud.inicio_empleado());
    }

    //CREAR BENEFICIARIO
    public Result guardarB(){
        Form<Beneficiario> boundForm = beneficiarioForm.bindFromRequest();
        String mensaje;
        System.out.println(boundForm.errors());
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.inicio_empleado.render(solicitudForm,beneficiarioForm));
        }
        Beneficiario beneficiario = boundForm.get();
        Ebean.save(beneficiario);
        flash("success",String.format("El beneficiario %s %s ha sido incluido con éxito.", beneficiario.getNombreB(), beneficiario.getApellidoB()));
        System.out.print(boundForm);
        return redirect(routes.CSolicitud.inicio_empleado());
    }
}
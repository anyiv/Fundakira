package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import io.ebean.*;

public class CSolicitud extends Controller{

    public Result solicitudes() {
        return ok(views.html.solicitudes.render());
    }

    public Result rp_solicitudes() {
        return ok(views.html.reporte_solicitudes.render());
    }

    public Result consulta_solicitud() {
        return ok(views.html.consultar_solicitud.render());
    }

    // CONSTRUCCION DE FORMULARIOS
    private Form<Solicitud> solicitudForm;

    @Inject
    public CSolicitud(FormFactory formFactory) {
        this.solicitudForm = formFactory.form(Solicitud.class);
    }

    //CREAR SOLICITUD
    public Result guardarS(){
        Form<Solicitud> boundForm = solicitudForm.bindFromRequest();
        String mensaje;

        return redirect(routes.HomeController.inicio());
    }
}
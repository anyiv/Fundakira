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

    public Result inicio() {
        return ok(views.html.inicio.render());
    }

    public Result solicitudes() {
        return ok(views.html.solicitudes.render());
    }

    public Result consulta_solicitud() {
        return ok(views.html.consultar_solicitud.render());
    }

    //CREAR SOLICITUD
    public Result guardarS(){
        //Form<Solicitud> boundForm = formularios.getSolicitudForm().bindFromRequest();
        String mensaje;

        return redirect(routes.CSolicitud.inicio());
    }
}
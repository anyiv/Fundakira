package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import io.ebean.*;

public class CServicio extends Controller{
    
    public Result listado_servicios() {
        return ok(views.html.servicios.render());
    }
    
    public Result incl_servicio() {
        return ok(views.html.incluir_servicio.render());
    }
}
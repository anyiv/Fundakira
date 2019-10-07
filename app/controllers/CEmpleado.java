package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import io.ebean.*;

public class CEmpleado extends Controller{
    
    public Result listado_empleados() {
        return ok(views.html.empleados.render());
    }

    public Result incl_empleado() {
        return ok(views.html.incluir_empleado.render());
    }

    public Result consultar_empleado(){
        return ok(views.html.consultar_empleado.render());
    }

    public Result inicio_admin() {
        return ok(views.html.inicio_admin.render());
    }
    
}
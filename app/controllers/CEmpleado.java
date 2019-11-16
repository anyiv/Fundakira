package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Empleado;
import io.ebean.*;

public class CEmpleado extends Controller{
    //Creacion de forms
    private Form<Empleado> empleadoForm;

    @Inject
    public CEmpleado(FormFactory formFactory) {
        this.empleadoForm = formFactory.form(Empleado.class);
    }
    
    public Result listado_empleados() {
        return ok(views.html.empleados.render());
    }
    //Incluir empleado
    public Result incl_empleado() {
        return ok(views.html.incluir_empleado.render(empleadoForm));
    }
    public Result guardarE () {
        Form<Empleado> boundForm = empleadoForm.bindFromRequest();
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos"); 
            return badRequest(views.html.incluir_empleado.render(boundForm));
        }
        Empleado empleado= boundForm.get();
        if (empleado.getCedulaE() == null){
            Ebean.save(empleado);
            flash("success",String.format("El empleado %s ha sido incluido con éxito.", empleado.getNombre()));
        } else {
        }
        
        return redirect(routes.CEmpleado.listado_empleados());
    }


    public Result consultar_empleado(){
        return ok(views.html.consultar_empleado.render());
    }

    public Result inicio_admin() {
        return ok(views.html.inicio_admin.render());
    }
    
}
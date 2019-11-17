package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Empleado;
import models.Fundacion;
import models.Usuario;
import models.Usuario_Empleado;
import models.TipoUser;
import io.ebean.*;

public class CEmpleado extends Controller{
    //Creacion de forms
    private Form<Empleado> empleadoForm;
    private Form<Usuario> usuarioForm;

    @Inject
    public CEmpleado(FormFactory formFactory) {
        this.empleadoForm = formFactory.form(Empleado.class);
        this.usuarioForm = formFactory.form(Usuario.class);
    }
    
    public Result listado_empleados() {
        return ok(views.html.empleados.render());
    }

    //CREAR EMPLEADO
    public Result incl_empleado() {
        return ok(views.html.incluir_empleado.render(empleadoForm, usuarioForm, Fundacion.buscador.listado()));
    }
    public Result guardarE() {
        Form<Empleado> boundFormE = empleadoForm.bindFromRequest();
        String cf = boundFormE.rawData().get("fundacion");
        Fundacion fundacion = Fundacion.buscador.porCodigo(UUID.fromString(cf));
        Form<Usuario> boundFormU = usuarioForm.bindFromRequest();
        if (boundFormU.hasErrors() || boundFormE.hasErrors()) { 
            flash("Error", "Por favor ingrese de nuevo los datos"); 
            return badRequest(views.html.incluir_empleado.render(boundFormE,boundFormU, Fundacion.buscador.listado()));
        }
        Empleado empleado = new Empleado(
            boundFormE.rawData().get("cedulaE"),
            boundFormE.rawData().get("nombre"),
            boundFormE.rawData().get("apellido"),
            boundFormE.rawData().get("direccion"),
            boundFormE.rawData().get("correo"),
            boundFormE.rawData().get("telefono"),
            'A',
            fundacion
        );
        Usuario usuario = boundFormU.get();
        String cedulae = empleado.getCedulaE();
        usuario.setEstatus('A');
        try{
            Empleado.buscador.porCedula(cedulae).getCedulaE();
            flash("error",String.format("El empleado ya existe."));
            return badRequest(views.html.incluir_empleado.render(boundFormE,boundFormU, Fundacion.buscador.listado()));
        } catch (Exception e){
            TipoUser tipUserEmp = TipoUser.buscador.porCodigo("2");
            usuario.setTipouser(tipUserEmp);
            Ebean.save(usuario);
            Usuario_Empleado ue = new Usuario_Empleado(usuario, empleado);
            Ebean.save(ue);
            empleado.setUsuario_empleado(ue);
            Ebean.save(empleado);
            flash("success",String.format("El empleado %s ha sido incluido con éxito.", empleado.getNombre()));
        }
        return redirect(routes.CEmpleado.listado_empleados());
    }

    public Result consultar_empleado(){
        return ok(views.html.consultar_empleado.render());
    }

    public modificar_empleado(String cedulaE){
        Form<Empleado> boundForm = empleadoForm.bindFromRequest();
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese datos en los campos a modificar."); 
            return badRequest(views.html.consultar_empleado.render(boundForm));
        }
        Empleado empleado = boundForm.get();
        if (empleado.getCedulaE() != null){
            Ebean.save(empleado);
            flash("success",String.format("Los datos del empleado  %s han sido modificados con éxito.", empleado.getNombre()));
        } else {
        }
        return redirect(routes.CEmpleado.listado_empleados());
    }

    public Result inicio_admin() {
        return ok(views.html.inicio_admin.render());
    }

    // ELIMINAR EMPLEADO
    public Result eliminar_empleado(String cedulaE) {
        Empleado empleado = Emleado.buscador.porCedula(cedulaE);
        empleado.setEstatus('I');
        Ebean.update(empleado);
        flash("success",String.format("EL empleado %s ha sido eliminado con éxito.", empleado.getNombre()));
        return redirect(routes.CEmpleado.empleado());
    }
    
}
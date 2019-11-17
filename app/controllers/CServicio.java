package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Servicio;
import models.Fundacion;
import io.ebean.*;

public class CServicio extends Controller{
    
    private Form<Servicio> servicioForm;

    @Inject
    public CServicio(FormFactory formFactory) {
        this.servicioForm = formFactory.form(Servicio.class);
    }

    public Result listado_servicios() {
        return ok(views.html.servicios.render(Fundacion.buscador.listado(), Servicio.buscador.listado()));
    }
    // CREAR Y MODIFICAR SERVICIO
    public Result incl_servicio() {
        return ok(views.html.incluir_servicio.render(servicioForm));
    }

    public Result guardarS() {
        Form<Servicio> boundForm = servicioForm.bindFromRequest();
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.incluir_servicio.render(boundForm));
        }
        Servicio servicio = boundForm.get();
        if (servicio.getCodServicio() == null){
            Ebean.save(servicio);
            flash("success",String.format("El servicio %s ha sido incluido con éxito.", servicio.getNombre()));
        } else {
            Ebean.update(servicio);
            flash("success",String.format("El servicio %s ha sido modificado con éxito.", servicio.getNombre()));
        }
        return redirect(routes.CServicio.listado_servicios());
    }

    // CARGA LOS DATOS PARA MODIFICARLOS
    public Result modificarS(UUID codServicio) {
        final Servicio servicio = Servicio.buscador.porCodigo(codServicio);
        if (servicio == null) {
            return notFound(String.format("El servicio %s no existe.", codServicio));
        }

        Form<Servicio> filledForm = servicioForm.fill(servicio);
        return ok(views.html.incluir_servicio.render(filledForm));
    }

    // ELIMINAR SERVICIO
    public Result eliminarS(final UUID codigo) {
        final Servicio servicio = Servicio.buscador.porCodigo(codigo);
        servicio.setEstatus('I');
        Ebean.update(servicio);
        flash("success",String.format("El servicio %s ha sido eliminado con éxito.", servicio.getNombre()));
        return redirect(routes.CServicio.listado_servicios());
    }
}
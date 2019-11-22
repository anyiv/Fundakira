package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Servicio;
import models.Fundacion;
import io.ebean.*;

@Singleton
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
    public Result incl_servicio(String cod) {
        UUID codigo = UUID.fromString(cod);
        return ok(views.html.incluir_servicio.render(servicioForm,Fundacion.buscador.porCodigo(codigo)));
    }

    public Result guardarS(String cod) {
        Form<Servicio> boundForm = servicioForm.bindFromRequest();
        UUID codigo = UUID.fromString(cod);
        Fundacion fundacion = Fundacion.buscador.porCodigo(codigo);
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.incluir_servicio.render(boundForm,Fundacion.buscador.porCodigo(codigo)));
        }
        UUID codserv;
        try{
            Servicio s = Servicio.buscador.porCodigo(UUID.fromString(boundForm.rawData().get("codServicio")));
            codserv = s.getCodServicio();
        } catch (Exception e){
            codserv = UUID.randomUUID();
        }
        Servicio servicio = new Servicio(
            codserv,
            boundForm.rawData().get("nombre"),
            boundForm.rawData().get("tipo"),
            Double.valueOf(boundForm.rawData().get("costo")),
            'A',
            fundacion
        );
        if (Servicio.buscador.porCodigo(servicio.getCodServicio()) == null){
            Ebean.save(servicio);
            flash("success",String.format("El servicio %s ha sido incluido con éxito.", servicio.getNombre()));
        } else {
            Ebean.update(servicio);
            flash("success",String.format("El servicio %s ha sido modificado con éxito.", servicio.getNombre()));
        }
        return redirect(routes.CServicio.listado_servicios());
    }

    // CARGA LOS DATOS PARA MODIFICARLOS
    public Result modificarS(UUID codServicio, String cod) {
        final Servicio servicio = Servicio.buscador.porCodigo(codServicio);
        UUID codigo = UUID.fromString(cod);
        if (servicio == null) {
            return notFound(String.format("El servicio %s no existe.", codServicio));
        }

        Form<Servicio> filledForm = servicioForm.fill(servicio);
        return ok(views.html.incluir_servicio.render(filledForm,Fundacion.buscador.porCodigo(codigo) ));
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
package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Fundacion;
import io.ebean.*;

public class CFundacion extends Controller {

    //Creación de forms
    private Form<Fundacion> fundacionForm;

    @Inject
    public CFundacion(FormFactory formFactory) {
        this.fundacionForm = formFactory.form(Fundacion.class);
    }

    public Result fundaciones() {
        return ok(views.html.fundaciones.render(Fundacion.buscador.listado()));
    }

    // CREAR Y MODIFICAR FUNDACION
    public Result incl_fundacion() {
        return ok(views.html.incluir_fundacion.render(fundacionForm));
    }

    public Result guardarF() {
        Form<Fundacion> boundForm = fundacionForm.bindFromRequest();
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.incluir_fundacion.render(boundForm));
        }
        Fundacion fundacion = boundForm.get();
        if (fundacion.getCod_fundacion() == null){
            Ebean.save(fundacion);
            flash("success",String.format("La fundación %s ha sido incluida con éxito.", fundacion.getNombre()));
        } else {
            Ebean.update(fundacion);
            flash("success",String.format("La fundación %s ha sido modificada con éxito.", fundacion.getNombre()));
        }
        return redirect(routes.CFundacion.fundaciones());
    }

    // CARGA LOS DATOS PARA MODIFICARLOS
    public Result modificarF(UUID cod_fundacion) {
        final Fundacion fundacion = Fundacion.buscador.porCodigo(cod_fundacion);
        if (fundacion == null) {
            return notFound(String.format("La fundacion %s no existe.", cod_fundacion));
        }

        Form<Fundacion> filledForm = fundacionForm.fill(fundacion);
        return ok(views.html.incluir_fundacion.render(filledForm));
    }

    // ELIMINAR FUNDACION
    public Result eliminarF(UUID codigo) {
        Fundacion fundacion = Fundacion.buscador.porCodigo(codigo);
        fundacion.setEstatus('I');
        Ebean.update(fundacion);
        flash("success",String.format("La fundación %s ha sido eliminada con éxito.", fundacion.getNombre()));
        return redirect(routes.CFundacion.fundaciones());
    }
}

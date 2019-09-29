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

    public Result fundaciones() {
        return ok(views.html.fundaciones.render(Fundacion.buscador.listado()));
    }

    // CONSTRUCCION DE FORMULARIOS
    private Form<Fundacion> fundacionForm;

    @Inject
    public CFundacion(FormFactory formFactory) {
        this.fundacionForm = formFactory.form(Fundacion.class);
    }

    // CREAR FUNDACION
    public Result incl_fundacion() {
        return ok(views.html.incluir_fundacion.render(fundacionForm));
    }

    public Result guardarF() {
        Form<Fundacion> boundForm = fundacionForm.bindFromRequest();
        String mensaje;
        if (boundForm.hasErrors()) { // aun no se sabe si
            flash("error", "Por favor haga bien el formulario."); // funciona
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

    // CONSULTAR Y MODIFICAR
    public Result consultarF(UUID cod_fundacion) {
        final Fundacion fundacion = Fundacion.buscador.porCodigo(cod_fundacion);
        if (fundacion == null) {
            return notFound(String.format("Fundacion %s does not exist.", cod_fundacion));
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

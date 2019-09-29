package controllers;

import play.mvc.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Fundacion;
import io.ebean.*;

public class CFundacion extends Controller {

    private Form<Fundacion> fundacionForm;

    @Inject
    public CFundacion(FormFactory formFactory) {
    this.fundacionForm = formFactory.form(Fundacion.class);
    }

    public Result incl_fundacion() {
        return ok(views.html.incluir_fundacion.render(fundacionForm));
    }

    public Result guardarF() {
        Form<Fundacion> boundForm = fundacionForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Por favor haga bien el formulario.");
            return badRequest(views.html.incluir_fundacion.render(boundForm));
          }
        Fundacion fundacion = boundForm.get();
        Ebean.save(fundacion);
        return redirect(routes.HomeController.fundaciones());
    }

//      public Result listaFundaciones(){ //lista de fundaciones
//         return TODO; //TODO significa que aun no se ha implementado
//     }

//     public Result createFundacion(){ 
//         return TODO;
//     }

//     public Result modificarF(String ean){ 
//         return TODO;
//     }

//     public Result guardarF(){
//         return TODO;
//     }

}
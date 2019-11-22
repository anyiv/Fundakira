package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;
import play.data.Form;
import play.data.FormFactory;
import models.Beneficiario;
import io.ebean.*;

@Singleton
public class CBeneficiario extends Controller {

    // CONSTRUCCION DE FORMULARIOS
    private Form<Beneficiario> beneficiarioForm;

    @Inject
    public CBeneficiario(FormFactory formFactory) {
        this.beneficiarioForm = formFactory.form(Beneficiario.class);
    }

    public Result inicio_ben() {
        return ok(views.html.inicio_ben.render());
    }

    // REGISTRAR BENEFICIARIO
    public Result reg_beneficiario() {
        return ok(views.html.registro_beneficiario.render(beneficiarioForm));
    }

    public Result guardarB() {
        Form<Beneficiario> boundForm = beneficiarioForm.bindFromRequest();
        String mensaje;
        if (boundForm.hasErrors()) { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.registro_beneficiario.render(boundForm));
        }
        Beneficiario beneficiario = boundForm.get();
        if (beneficiario.getCedulaB() == null){
            Ebean.save(beneficiario);
            flash("success",String.format("Registro exitoso"));
        }
        return redirect(routes.CLogin.index());       
    }
}

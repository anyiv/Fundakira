package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;
import play.data.Form;
import play.data.FormFactory;
import models.Beneficiario;
import models.Usuario;
import models.Usuario_Beneficiario;
import models.TipoUser;
import models.Solicitud;
import io.ebean.*;

@Singleton
public class CBeneficiario extends Controller {

    // CONSTRUCCION DE FORMULARIOS
    private Form<Beneficiario> beneficiarioForm;
    private Form<Usuario> usuarioForm;

    @Inject
    public CBeneficiario(FormFactory formFactory) {
        this.beneficiarioForm = formFactory.form(Beneficiario.class);
        this.usuarioForm = formFactory.form(Usuario.class);
    }

    public Result inicio_ben(String ced) {
        return ok(views.html.inicio_ben.render(Solicitud.buscador.porBeneficiario(ced)));
    }

    // REGISTRAR BENEFICIARIO
    public Result reg_beneficiario() {
        return ok(views.html.registro_beneficiario.render(beneficiarioForm, usuarioForm));
    }

    public Result guardarBen() {
        Form<Beneficiario> boundForm = beneficiarioForm.bindFromRequest();
        Form<Usuario> boundFormU = usuarioForm.bindFromRequest();
        if (boundForm.hasErrors() || boundFormU.hasErrors() )
        { 
            flash("error", "Por favor ingrese de nuevo los datos del formulario."); 
            return badRequest(views.html.registro_beneficiario.render(boundForm, boundFormU));
        }
        System.out.print(boundForm);
        System.out.print(boundFormU);
        Beneficiario beneficiario = boundForm.get();
        Usuario usuario = boundFormU.get();
        Usuario_Beneficiario ub = new Usuario_Beneficiario();
        TipoUser tp = TipoUser.buscador.porCodigo("3");
        ub.setUsuario(usuario);
        ub.setBeneficiario(beneficiario);
        usuario.setTipouser(tp);
        Ebean.save(beneficiario);
        Ebean.save(usuario);
        Ebean.save(ub);
        flash("success",String.format("El beneficiario %s %s ha sido incluido con éxito.", beneficiario.getNombreB(), beneficiario.getApellidoB()));        
        return redirect(routes.CLogin.index());   
    }
}

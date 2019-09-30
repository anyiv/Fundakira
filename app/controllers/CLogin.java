package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Usuario;
import models.Empleado;
import io.ebean.*;

public class CLogin extends Controller {

    // CONSTRUCCION DE FORMULARIOS
    private Form<Usuario> loginForm;

    @Inject
    public CLogin(FormFactory formFactory) {
        this.loginForm = formFactory.form(Usuario.class);
    }

    // RENDER DEL LOGIN

    public Result index() {
        return ok(views.html.index.render(loginForm));
    }

    // INICIO DE SESIÓN

    public Result login(Http.Request request) {
        Form<Usuario> boundForm = loginForm.bindFromRequest();

        Usuario usuario = boundForm.get();
        if (usuario.buscador.login(usuario.getCedula_E(),usuario.getContrasenna())!=null){
            flash("success",String.format("Bienvenido, %s.",Empleado.buscador.porCedula(usuario.getCedula_E()).toString()));
            return redirect(routes.HomeController.inicio()).addingToSession(request, "user", usuario.getCedula_E());
        } else {
            flash("error_login",String.format("Error al iniciar sesión. Verifique sus datos y vuelva a intentar."));
        }
        return badRequest(views.html.index.render(boundForm));
    }

    // LOGOUT

    public Result logout() {
        return redirect(routes.CLogin.index()).withNewSession();
    }
}

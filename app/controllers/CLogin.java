package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Usuario;
import models.Empleado;
import models.Login;
import io.ebean.*;

public class CLogin extends Controller {

    // CONSTRUCCION DE FORMULARIOS
    private Form<Login> loginForm;

    @Inject
    public CLogin(FormFactory formFactory) {
        this.loginForm = formFactory.form(Login.class);
    }

    // RENDER DEL LOGIN

    public Result index() {
        return ok(views.html.index.render(loginForm));
    }

    // INICIO DE SESIÓN

    public Result login(Http.Request request) {
        Form<Login> boundForm = loginForm.bindFromRequest();
        Login login = boundForm.get();
        try {
            if (Usuario.buscador.login(login.getCedula(),login.getContrasenna())!=null){
                flash("success",String.format("Bienvenido, %s.",Empleado.buscador.porCedula(login.getCedula()).toString()));
                return redirect(routes.HomeController.inicio()).addingToSession(request, "user", login.getCedula());
            } else {
                flash("error_login",String.format("Error al iniciar sesión. Verifique sus datos y vuelva a intentar."));
            }
        } catch (Exception e) {
            flash("error_login",String.format("Error al iniciar sesión. Verifique sus datos y vuelva a intentar."));
            return badRequest(views.html.index.render(boundForm));
        }


        return badRequest(views.html.index.render(boundForm));
    }

    // LOGOUT

    public Result logout() {
        return redirect(routes.CLogin.index()).withNewSession();
    }
}

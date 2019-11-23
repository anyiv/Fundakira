package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;
import play.data.Form;
import play.data.FormFactory;
import models.Usuario;
import models.Beneficiario;
import models.Empleado;
import models.Login;
import io.ebean.*;

@Singleton
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
        Usuario usuario = Usuario.buscador.login(login.getCedula(),login.getContrasenna());
        switch (usuario.getEstatus()) {
            case 'A':
                switch (Integer.valueOf(usuario.getTipouser().getCodTipoUser())) {
                    case 1:
                        flash("success",String.format("Bienvenido, %s.",Empleado.buscador.porCedula(login.getCedula()).toString()));
                        return redirect(routes.CEmpleado.inicio_admin()).addingToSession(request, "user", login.getCedula());
                
                    case 2:
                        flash("success",String.format("Bienvenido, %s.",Empleado.buscador.porCedula(login.getCedula()).toString()));
                        return redirect(routes.CSolicitud.inicio_empleado()).addingToSession(request, "user", login.getCedula());

                    // case 3:
                    //     flash("success",String.format("Bienvenido, %s.",Beneficiario.buscador.porCedula(login.getCedula()).toString()));
                    //     return redirect(routes.CBeneficiario.inicio_ben()).addingToSession(request, "user", login.getCedula());

                    default:
                        break;
                }
                break;
        
            case 'D':
                flash("error_login",String.format("Datos incorrectos."));
                return badRequest(views.html.index.render(boundForm));

            case 'N':
                flash("error_login",String.format("Usuario no encontrado."));
                return badRequest(views.html.index.render(boundForm));

            default:
                break;
        }
        return badRequest(views.html.index.render(boundForm));
    }

    // LOGOUT

    public Result logout() {
        return redirect(routes.CLogin.index()).withNewSession();
    }
}

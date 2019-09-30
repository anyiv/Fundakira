package controllers;

import play.mvc.*;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Servicio;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message. The configuration
     * in the <code>routes</code> file means that this method will be called when
     * the application receives a <code>GET</code> request with a path of
     * <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result inicio() {
        return ok(views.html.inicio.render());
    }

    public Result servicios() {
        return ok(views.html.servicios.render());
    }

    public Result reportes() {
        return ok(views.html.reportes.render());
    }

    public Result rp_solicitantes() {
        return ok(views.html.reporte_solicitantes.render());
    }

    public Result incl_servicio() {
        return ok(views.html.incluir_servicio.render());
    }

    public Result rp_presupuesto() {
        return ok(views.html.reporte_presupuesto.render());
    }

    public Result empleados() {
        return ok(views.html.empleados.render());
    }

    public Result incl_empleado() {
        return ok(views.html.incluir_empleado.render());
    }

    public Result inicio_admin() {
        return ok(views.html.inicio_admin.render());
    }


    public Result consultar_empleado(){
        return ok(views.html.consultar_empleado.render());
    }

}

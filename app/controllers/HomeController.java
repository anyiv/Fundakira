package controllers;

import play.mvc.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(views.html.index.render());
    }

    public Result inicio() {
        return ok(views.html.inicio.render());
    }

    public Result fundaciones() {
        return ok(views.html.fundaciones.render());
    }

    public Result servicios() {
        return ok(views.html.servicios.render());
    }

    public Result solicitudes() {
        return ok(views.html.solicitudes.render());
    }

    public Result reportes() {
        return ok(views.html.reportes.render());
    }

    public Result incl_fundacion() {
        return ok(views.html.incluir_fundacion.render());
    }

    public Result rp_solicitantes() {
        return ok(views.html.reporte_solicitantes.render());
    }
}


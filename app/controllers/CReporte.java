package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.Inject;

import buscadores.BuscadorEmpleado;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Beneficiario;
import models.Empleado;
import models.Servicio;
import models.Fundacion;
import models.buscadores.*;
import io.ebean.*;

public class CReporte extends Controller{
    
    public Result lista_reportes() {
        return ok(views.html.reportes.render());
    }
    
    public Result rp_solicitantes(UUID codfund) {
        BuscadorEmpleado be = new BuscadorEmpleado();
        //List<Empleado> empleados_fundacion = be.
        return ok(views.html.reporte_solicitantes.render(Fundacion.buscador.listado()));
    }
    
    public Result rp_presupuesto() {
        return ok(views.html.reporte_presupuesto.render());
    }

    public Result rp_solicitudes() {
        return ok(views.html.reporte_solicitudes.render());
    }
}
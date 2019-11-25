package controllers;

import play.mvc.*;
import java.util.*;

import java.lang.ProcessBuilder.Redirect;

import javax.inject.*;

import buscadores.BuscadorEmpleado;
import buscadores.BuscadorSolicitud;
import play.data.Form;
import play.data.FormFactory;
import models.Solicitud;
import models.Beneficiario;
import models.Empleado;
import models.Servicio;
import models.Fundacion;
import models.Gobernacion;
import buscadores.*;
import io.ebean.*;
import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Singleton
public class CReporte extends Controller{
    
    public Result lista_reportes() {
        return ok(views.html.reportes.render());
    }
    
    public Result rp_solicitantes() {
        return ok(views.html.reporte_solicitantes.render(Fundacion.buscador.listado(),Beneficiario.buscador.listado()));
    }

    public Result repSoli(UUID codfund){
        BuscadorEmpleado be = new BuscadorEmpleado();
        BuscadorSolicitud bs = new BuscadorSolicitud();
        Fundacion fun = Fundacion.buscador.porCodigo(codfund);
        List<Empleado> empleados_fundacion = be.porFundacion(codfund);
        List<Solicitud> solicitudes_fundacion = new ArrayList<Solicitud>();
        List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
        for (Empleado empleado : empleados_fundacion) {
            List<Solicitud> sol_empleado = bs.porEmpleado(empleado.getCedulaE());
            for (Solicitud sol : sol_empleado) {
                solicitudes_fundacion.add(sol);
            }
        }
        for (Solicitud sol : solicitudes_fundacion) {
            if(!beneficiarios.contains(sol.getBeneficiario())){
                beneficiarios.add(sol.getBeneficiario());
            }
        }
        return ok(views.html.r_solicitantes.render(beneficiarios,Fundacion.buscador.listado(),fun));
    }
    
    @BodyParser.Of(BodyParser.Json.class)
    public Result calcularPresupuesto(Http.Request request) {
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        UUID codfun = UUID.fromString(json.findPath("fundacion").textValue());
        Fundacion fund = Fundacion.buscador.porCodigo(codfun);
        System.out.print(codfun);
        respuesta.put("disponible",fund.getMontoDisponible());
        respuesta.put("porcgastado",fund.getPorcGastado());
        return ok(respuesta);
    }

    public Result rp_presupuesto() {
        return ok(views.html.reporte_presupuesto.render(Fundacion.buscador.listado(),Solicitud.buscador.listadoTodos()));
    }

    public Result rp_solicitudes() {
        return ok(views.html.reporte_solicitudes.render(Empleado.buscador.listado(),Solicitud.buscador.listadoTodos()));
    }

    public Result calcularSoliAp(Http.Request request) throws Exception{
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        UUID codfun = UUID.fromString(json.findPath("fundacion").textValue());
        String fechaDesde = json.findPath("fechaDesde").textValue();
        String fechaHasta = json.findPath("fechaHasta").textValue();
        Fundacion fund = Fundacion.buscador.porCodigo(codfun);
        System.out.print(codfun);
        respuesta.put("costo",fund.getCostoSolAp(fechaDesde, fechaHasta));
        return ok(respuesta);
    }

    public Result costoPreGenerado(Http.Request request) throws Exception{
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        String fdesde = json.findPath("fdesde").textValue();
        String fhasta = json.findPath("fhasta").textValue();
        Gobernacion gob = new Gobernacion();
        respuesta.put("presupuesto",gob.calPresupuesto(fdesde, fhasta));
        return ok(respuesta);
    }
}
package controllers;

import play.mvc.*;
import java.util.*;

import javax.inject.*;
import models.Solicitud;
import models.Beneficiario;
import models.DetalleSolicitud;
import models.Usuario_Beneficiario;
import models.Servicio;
import models.Empleado;
import models.Fundacion;
import io.ebean.*;
import play.libs.Json;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class FacadeCrearSolicitud extends Controller{

    private Beneficiario ben = new Beneficiario();
    private Empleado emp = new Empleado();
    private Solicitud soli = new Solicitud();
    private Fundacion f = new Fundacion();
    private DetalleSolicitud ds = new DetalleSolicitud();

    public FacadeCrearSolicitud(){
        
    }

    public Result crearSolicitud(Http.Request request){
        JsonNode json = request.body().asJson();
        ObjectNode respuesta = Json.newObject();
        String cedulab = json.findPath("cedulab").textValue();
        List<Servicio> servicios = new ArrayList<Servicio>();
        for (JsonNode codservicio : json.withArray("servicios")) {
            servicios.add(Servicio.buscador.porCodigo(UUID.fromString(codservicio.textValue())));
        }
        String cedulae = request.session().getOptional("user").get();
        String pri = json.findPath("prioridad").textValue();
        String otradonac = json.findPath("otdonac").textValue();
        String razon = json.findPath("razon").textValue();
        ben = Beneficiario.buscador.porCedula(cedulab);
        emp = Empleado.buscador.porCedula(cedulae);
        Date fechaRegistro = new Date();
        soli = new Solicitud(
            UUID.randomUUID(),
            emp,
            ben,
            otradonac,
            razon,
            pri,
            fechaRegistro,
            'P'
        );
        try {
            Ebean.save(soli);
            f = soli.getEmpleado().getFundacion();
            double presupuesto = 0;
            for (Servicio servicio : servicios) {
                ds = new DetalleSolicitud(soli, servicio, servicio.getCosto());
                Ebean.save(ds);
                presupuesto += ds.getCosto();
            }
            if (f.getMontoDisponible()-presupuesto<0){
                respuesta.put("resultado","La solicitud ha sido rechazada automáticamente por falta de presupuesto.");
                soli.setEstatus('N');
                soli.setRazon("Falta de presupuesto.");
                Ebean.update(soli);
            } else {
                respuesta.put("resultado","La solicitud se ha creado con éxito.");
            }
            return ok(respuesta);
        } catch (Exception e) {
            respuesta.put("resultado","Hubo un error creando la solicitud.");
            return ok(respuesta);
        }
    }

}
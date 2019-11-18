package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Solicitud;
import models.Empleado;
import buscadores.BuscadorEmpleado;

public class BuscadorSolicitud extends Finder<UUID,Solicitud> {

    public BuscadorSolicitud() {
      super(Solicitud.class);
    }
  
    // Add customer finder methods ...
  
    public Solicitud porCodigo(UUID codigo) {
      return query().where().eq("cod_solicitud", codigo.toString()).findOne();
    }
  
    public List<Solicitud> listadoPendientes() {
      return query().where().eq("estatus", "P").findList();
    }

    public List<Solicitud> listadoAprobadas() {
        return query().where().eq("estatus", "A").findList();
      }
    
    public List<Solicitud> listadoNegadas() {
    return query().where().eq("estatus", "N").findList();
    }

    public List<Solicitud> listadoTodos() {
        return query().findList();
      }

    public List<Solicitud> porEmpleado(String cedula){
      return query().where().eq("cedulaE",cedula).findList();
    }

    // public List<Solicitud> porFundacion(UUID codigo){
    //   BuscadorEmpleado be = new BuscadorEmpleado();
    //   List<Empleado> empleados_fundacion = be.porFundacion(codigo);
      
    //   return query().where().eq("");
    // }
}
package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Empleado;

public class BuscadorEmpleado extends Finder<String,Empleado> {

    public BuscadorEmpleado() {
      super(Empleado.class);
    }
  
    // Add customer finder methods ...

    public Empleado porCedula(String cedula){
      return query()
              .where()
              .eq("cedula_e",cedula)
              .findOne();
    }
  
    public List<Empleado> listado() {
      return query().where().eq("estatus", "A").findList();
    }

    public List<Empleado> porFundacion(UUID codigo) {
      return query().where().eq("codFundacion", codigo.toString()).and().eq("estatus","A").findList();
    }
  }
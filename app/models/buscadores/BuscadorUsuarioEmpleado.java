package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Usuario_Empleado;;

public class BuscadorUsuarioEmpleado extends Finder<UUID,Usuario_Empleado> {

    public BuscadorUsuarioEmpleado() {
      super(Usuario_Empleado.class);
    }
  
    // Add customer finder methods ...
  
    public Usuario_Empleado porCedula(String cedula) {
      return query().where().eq("cedulae", cedula).findOne();
    }
  }
package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Usuario;

public class BuscadorUsuario extends Finder<String,Usuario> {

    public BuscadorUsuario() {
      super(Usuario.class);
    }
  
    // Add customer finder methods ...
  
    public Usuario login(String cedula, String contrasenna) {
      return query()
              .where()
              .and()
                .eq("cedula_E", cedula)
                .eq("contrasenna", contrasenna)
                .eq("estatus","A")
              .findOne();
    }

    public Usuario porCedula(String cedula){
      return query()
              .where()
              .eq("cedula_E",cedula)
              .findOne();
    }
  
    public List<Usuario> listado() {
      return query().where().eq("estatus", "A").findList();
    }
  }
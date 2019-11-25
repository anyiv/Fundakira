package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Gobernacion;

public class BuscadorGobernacion extends Finder<String,Gobernacion> {

    public BuscadorGobernacion() {
      super(Gobernacion.class);
    }
  
    // Add customer finder methods ...
  
    public Gobernacion porCodigo(String codigo) {
      return query().where().eq("codGobernacion", codigo).findOne();
    }

    public List<Gobernacion> listado() {
      return query().findList();
    }
  }
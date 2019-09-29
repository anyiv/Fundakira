package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Fundacion;

public class BuscadorFundacion extends Finder<UUID,Fundacion> {

    public BuscadorFundacion() {
      super(Fundacion.class);
    }
  
    // Add customer finder methods ...
  
    public Fundacion porCodigo(UUID codigo) {
      return query().where().eq("cod_fundacion", codigo.toString()).findOne();
    }
  
    public List<Fundacion> listado() {
      return query().where().eq("estatus", "A").findList();
    }
  }
package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Servicio;

public class BuscadorServicio extends Finder<String,Servicio>{

    public BuscadorServicio() {
        super(Servicio.class);
      }

    public List<Servicio> listado() {
    return query().where().eq("estatus", "A").findList();
    }
}

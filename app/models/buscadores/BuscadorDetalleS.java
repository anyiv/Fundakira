package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.DetalleSolicitud;

public class BuscadorDetalleS extends Finder<UUID,DetalleSolicitud> {

    public BuscadorDetalleS() {
      super(DetalleSolicitud.class);
    }
  
    // Add customer finder methods ...
  
    public DetalleSolicitud porCodigo(UUID codigo) {
      return query().where().eq("cod_solicitud", codigo.toString()).findOne();
    }

    public int contarDetalles(UUID codigo){
        return query().where().eq("cod_solicitud", codigo.toString()).findCount();
    }

    public List<DetalleSolicitud> listadoDet(UUID codigo) {
      return query().where().eq("cod_solicitud", codigo.toString()).findList();
    }
  
  }
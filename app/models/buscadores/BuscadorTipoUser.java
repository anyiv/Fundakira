package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.TipoUser;;

public class BuscadorTipoUser extends Finder<String,TipoUser>{
    
    public BuscadorTipoUser() {
        super(TipoUser.class);
    }

    public TipoUser porCodigo(String codigo) {
        return query().where().eq("codTipoUser", codigo.toString()).findOne();
      }

    public List<TipoUser> listado() {
    return query().where().eq("estatus", "A").findList();
    }
}
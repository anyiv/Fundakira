package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Beneficiario;

public class BuscadorBeneficiario extends Finder<String,Beneficiario> {

    public BuscadorBeneficiario() {
      super(Beneficiario.class);
    }
  
    // Add customer finder methods ...

    public Beneficiario porCedula(String cedula){
      return query()
              .where()
              .eq("cedulaB",cedula)
              .findOne();
    }
  
    public List<Beneficiario> listado() {
      return query().where().eq("estatusB", "A").findList();
    }
  }
package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Beneficiario;
import models.Fundacion;
import models.TipoUser;
import models.Usuario;
import models.Usuario_Beneficiario;

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

    public Beneficiario porCedulaValidado(String cedula){
      Beneficiario ben = new Beneficiario();
      try{
          ben = query()
          .where()
          .eq("cedulaB",cedula)
          .findOne();
          ben.getNombreB();
      } catch (Exception e){
        ben = new Beneficiario();
        ben.setNombreB("");
        ben.setApellidoB("");
        Usuario_Beneficiario ub = new Usuario_Beneficiario();
        Usuario u = new Usuario();
        TipoUser tu = new TipoUser("-1", "", 'A');
        u.setTipouser(tu);
        ub.setUsuario(u);
        ben.setUsuario_beneficiario(ub);
      }
      return ben;
    }
  
    public List<Beneficiario> listado() {
      return query().where().eq("estatusB", "A").findList();
    }
  }
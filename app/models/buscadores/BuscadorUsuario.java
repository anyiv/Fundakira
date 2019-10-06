package buscadores;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import io.ebean.Finder;
import play.data.format.*;
import play.data.validation.*;
import models.Usuario;
import models.Empleado;
import models.Beneficiario;
import models.Usuario_Empleado;
import models.Usuario_Beneficiario;

public class BuscadorUsuario extends Finder<String,Usuario> {

    private Finder<String,Usuario_Empleado> user_empleado;
    private Finder<String,Usuario_Beneficiario> user_beneficiario;
    private Finder<UUID,Usuario> usuario;

    public BuscadorUsuario() {
      super(Usuario.class);
      user_empleado = new Finder<>(Usuario_Empleado.class);
      user_beneficiario = new Finder<>(Usuario_Beneficiario.class);
      usuario = new Finder<>(Usuario.class);
    }
  
    // Add customer finder methods ...
  
    public Usuario login(String cedula, String contrasenna) {
      Usuario_Empleado empleado = user_empleado.query().where().eq("cedulae",cedula).findOne();
      Usuario user = empleado.getUsuario();
      if (user.getContrasenna() == contrasenna && user.getEstatus() == 'A'){
        return user;
      }
      user = new Usuario();
      /*return query()
              .where()
              .and()
                .eq("codUsuario", user.getCodUsuario().toString())
                .eq("contrasenna", contrasenna)
                .eq("estatus","A")
              .findOne();
              */
      return user;
    }

    public Usuario porCedula(String cedula){
      return query()
              .where()
              .eq("cedula_Emp",cedula)
              .findOne();
    }
  
    public List<Usuario> listado() {
      return query().where().eq("estatus", "A").findList();
    }
  }
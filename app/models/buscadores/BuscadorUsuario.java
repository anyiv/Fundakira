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
      Usuario user = new Usuario();
      try{
        System.out.print("Busco el empleado\n");
        Usuario_Empleado empleado = user_empleado.query().where().eq("cedulae",cedula).findOne();
        System.out.print("Encontre el empleado\n");
        user = empleado.getUsuario();
        System.out.print(contrasenna + "\n");
        System.out.print(user.getContrasenna() + "\n");
        System.out.print(user.getContrasenna().toString() == contrasenna.toString());
        if (user.getContrasenna().equals(contrasenna) && user.getEstatus() == 'A'){
          System.out.print("\nTodo bien, debería acceder el empleado");
        } else {
          user.setEstatus('D');
        }
      } catch (Exception e){
        try{
          System.out.print("Busco el beneficiario\n");
          Usuario_Beneficiario uben = user_beneficiario.query().where().eq("cedulab",cedula).findOne();
          System.out.print("Encontre el beneficiario\n");
          user = uben.getUsuario();
          System.out.print(contrasenna + "\n");
          System.out.print(user.getContrasenna() + "\n");
          System.out.print(user.getContrasenna().toString() == contrasenna.toString());
          if (user.getContrasenna().equals(contrasenna) && user.getEstatus() == 'A'){
            System.out.print("\nTodo bien, debería acceder el beneficiario");
          } else {
            user.setEstatus('D');
          }
        } catch (Exception f){
          user.setEstatus('N');
          return user;
        }
      }
      return user;
    }

    public Usuario loginB(String cedula, String cont){
      Usuario_Beneficiario uben = user_beneficiario.query().where().eq("cedulab",cedula).findOne();
      Usuario user = uben.getUsuario();
      if (user.getContrasenna() == cont && user.getEstatus() == 'A'){
        return user;
      }
      user = new Usuario();
      return user;
    }

    // public Usuario porCedula(String cedula){
    //   return query()
    //           .where()
    //           .eq("cedula_Emp",cedula)
    //           .findOne();
    // }
    
    public List<Usuario> listado() {
      return query().where().eq("estatus", "A").findList();
    }
  }
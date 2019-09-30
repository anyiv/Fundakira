package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorEmpleado;

@Entity
@Table(name="Empleado")
public class Empleado extends Model {

    @Id
    @Column(length=9)
    public String cedula_Emp;

    @OneToOne
    public Usuario usuario;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length = 30 )
    public String nombre;

    @Constraints.Required(message = "Por favor ingrese el apellido")
    @Column(length = 30)
    public String apellido;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length = 50)
    public String direccion;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    public String correo;

    @Column(length=12)
    public String telefono;

    @Column(length=1)
    public char estatus;

    @OneToOne
    public Fundacion fundacion; //El que recibe la clave foranea

    public String getCedula_Emp() {
        return this.cedula_Emp;
    }

    public void setCedula_Emp(String cedula_Emp) {
        this.cedula_Emp = cedula_Emp;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public Fundacion getFundacion() {
        return this.fundacion;
    }

    public void setFundacion(Fundacion fundacion) {
        this.fundacion = fundacion;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.apellido;
    }

    public static BuscadorEmpleado buscador = new BuscadorEmpleado();
    
} 
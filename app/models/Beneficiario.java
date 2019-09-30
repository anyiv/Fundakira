package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Beneficiario")
public class Beneficiario extends Model {

    @Id
    @Column(length=9,unique=true) 
    private String cedulaB;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length=30)
    private String nombre;

    @Constraints.Required(message = "Por favor ingrese el apellido")
    @Column(length=30)
    private String apellido;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length=50)
    private String direccion;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    private String correo;

    @Column(length=12)
    private String telefono;

    @Column(length=1)
    private char estatus;

    public String getCedulaB() {
        return this.cedulaB;
    }

    public void setCedulaB(String cedulaB) {
        this.cedulaB = cedulaB;
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

    public static Finder<Integer, Beneficiario> find = new Finder<>(Beneficiario.class);
    
} 
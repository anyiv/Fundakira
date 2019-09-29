package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorFundacion;

@Entity
@Table(name="Fundacion")
public class Fundacion extends Model {

    @Id 
    @Column(length=9)
    private UUID cod_fundacion;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length = 30 )
    private String nombre;

    @Constraints.Required
    @Column(length = 30)
    private double porcPartida;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length = 50)
    private String direccion;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    private String correo;

    @Column(length=12)
    private String telefono;

    @Column(length=1)
    private char estatus;

    @OneToMany(mappedBy="fundacion",cascade = CascadeType.ALL)
    public List<Servicio> servicios;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "fundacion") //el que da la clave foranea
    public Empleado empleado;

    public UUID getCod_fundacion() {
        return this.cod_fundacion;
    }

    public void setCod_fundacion(UUID cod_fundacion) {
        this.cod_fundacion = cod_fundacion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcPartida() {
        return this.porcPartida;
    }

    public void setPorcPartida(double porcPartida) {
        this.porcPartida = porcPartida;
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

    public List<Servicio> getServicios() {
        return this.servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    @Override
    public String toString() {
        return "{" + this.nombre + " " + this.estatus +
            "}";
    }

    public static final BuscadorFundacion buscador = new BuscadorFundacion();
}
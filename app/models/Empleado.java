package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorEmpleado;
import buscadores.BuscadorUsuarioEmpleado;

@Entity
@Table(name="Empleado")
public class Empleado extends Model {

    @Id
    @Column(length=9)
    public String cedulaE;

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

    @ManyToOne
    @JoinColumn(name="codFundacion")
    public Fundacion fundacion; //El que recibe la clave foranea

    //clave foranea para la solicitud
    @OneToMany(mappedBy="empleado",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Solicitud> solicitud;

    @OneToOne (mappedBy = "empleado", fetch = FetchType.LAZY)
    public Usuario_Empleado usuario_empleado;

    public Empleado(){
        
    }

    public String getCedulaE() {
        return this.cedulaE;
    }

    public void setCedulaE(String cedulaE) {
        this.cedulaE = cedulaE;
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

    public List<Solicitud> getSolicitud() {
        return this.solicitud;
    }

    public void setSolicitud(List<Solicitud> solicitud) {
        this.solicitud = solicitud;
    }

    public Usuario_Empleado getUsuario_empleado() {
        return this.usuario_empleado;
    }

    public void setUsuario_empleado(Usuario_Empleado usuario_empleado) {
        this.usuario_empleado = usuario_empleado;
    }


    public Empleado(String cedulaE, String nombre, String apellido, String direccion, String correo, String telefono, char estatus, Fundacion fundacion, List<Solicitud> solicitud, Usuario_Empleado usuario_empleado) {
        this.cedulaE = cedulaE;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.estatus = estatus;
        this.fundacion = fundacion;
        this.solicitud = solicitud;
        this.usuario_empleado = usuario_empleado;
    }

    public Empleado(String cedulaE, String nombre, String apellido, String direccion, String correo, String telefono, char estatus, Fundacion fundacion) {
        this.cedulaE = cedulaE;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.estatus = estatus;
        this.fundacion = fundacion;
    }

    public String getTipoU(){
        BuscadorUsuarioEmpleado bu = new BuscadorUsuarioEmpleado();
        Usuario_Empleado ue = bu.porCedula(this.cedulaE);
        return ue.getUsuario().getTipouser().getCodTipoUser();
    }

    @Override
    public String toString() {
        return this.getNombre() + " " + this.getApellido();
    }


    public static BuscadorEmpleado buscador = new BuscadorEmpleado();
    
} 
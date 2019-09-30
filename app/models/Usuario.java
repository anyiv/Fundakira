package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorUsuario;

@Entity
@Table(name="Usuario")
public class Usuario extends Model{

    @Id 
    @Column(length=9)
    public String cedula_E;

    @OneToOne
    @PrimaryKeyJoinColumn(name="cedula_E")
    public Empleado empleado;

    @Constraints.Required(message = "Por favor ingrese la contraseña")
    @Column(length = 30)
    public String contrasenna;

    @Column(length=1)
    public char estatus;

    public String getCedula_E() {
        return this.cedula_E;
    }

    public void setCedula_E(String cedula_E) {
        this.cedula_E = cedula_E;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getContrasenna() {
        return this.contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public static final BuscadorUsuario buscador = new BuscadorUsuario();
}
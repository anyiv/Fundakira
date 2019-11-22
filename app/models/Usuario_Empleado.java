package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorEmpleado;

@Entity
@Table(name="Usuario_Empleado")
public class Usuario_Empleado extends Model {

    public Usuario_Empleado(Usuario usuario, Empleado empleado) {
        this.usuario = usuario;
        this.empleado = empleado;
    }

    public Usuario_Empleado() {
        
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="codUsuario")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cedulaE")
    private Empleado empleado;

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
}
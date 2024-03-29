package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorServicio;
@Entity
@Table(name="Servicio")
public class Servicio extends Model {
    @Id 
    @Column(length=9)
    @GeneratedValue
    public UUID codServicio;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length = 30)
    public String nombre;

    @Constraints.Required(message = "Por favor ingrese el tipo")
    @Column(length=10)
    public String tipo;

    @Constraints.Required(message = "Por favor ingrese el costo")
    @Column(length=15)
    public double costo;

    @Column(length=1)
    public char estatus;

    @ManyToOne(optional=false,fetch = FetchType.LAZY)
    @JoinColumn(name="cod_fundacion")
    public Fundacion fundacion;

    //clave foranea del detalle de solicitud
    @OneToMany(mappedBy="servicio",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detallesolicitud;


    public UUID getCodServicio() {
        return this.codServicio;
    }

    public void setCodServicio(UUID codServicio) {
        this.codServicio = codServicio;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
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

    public List<DetalleSolicitud> getDetallesolicitud() {
        return this.detallesolicitud;
    }

    public void setDetallesolicitud(List<DetalleSolicitud> detallesolicitud) {
        this.detallesolicitud = detallesolicitud;
    }
    

    public Servicio(UUID codServicio, String nombre, String tipo, double costo, char estatus, Fundacion fundacion, List<DetalleSolicitud> detallesolicitud) {
        this.codServicio = codServicio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costo = costo;
        this.estatus = estatus;
        this.fundacion = fundacion;
        this.detallesolicitud = detallesolicitud;
    }

    public Servicio(UUID codServicio, String nombre, String tipo, double costo, char estatus, Fundacion fundacion) {
        this.codServicio = codServicio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.costo = costo;
        this.estatus = estatus;
        this.fundacion = fundacion;
    }

    public Servicio(){
        
    }

    public static final BuscadorServicio buscador = new BuscadorServicio();
}
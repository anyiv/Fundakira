package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="DetalleSolicitud")
public class DetalleSolicitud extends Model {

    //clave foranea de la solicitud
    @ManyToOne
    @JoinColumn(name="cod_solicitud")
    private Solicitud solicitud;

    //clave foranea del servicio
    @ManyToOne
    @JoinColumn(name="cod_servicio")
    private Servicio servicio;

    @Column(length=10)
    private double costo;


    public Solicitud getSolicitud() {
        return this.solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    public Servicio getServicio() {
        return this.servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public double getCosto() {
        return this.costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public DetalleSolicitud(Solicitud solicitud, Servicio servicio, double costo) {
        this.solicitud = solicitud;
        this.servicio = servicio;
        this.costo = costo;
    }

}
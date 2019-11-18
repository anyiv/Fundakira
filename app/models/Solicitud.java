package models;

import java.lang.annotation.Inherited;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorSolicitud;
import models.DetalleSolicitud;

@Entity
@Table(name="Solicitud")
public class Solicitud extends Model{

    @Id 
    @Column(length=9)
    private UUID cod_solicitud;

    //clave foranea de empleado
    @ManyToOne
    @JoinColumn(name="cedulaE")
    public Empleado empleado;

    //clave foranea del beneficiario
    @ManyToOne
    @JoinColumn(name="cedulaB")
    public Beneficiario beneficiario;

    //clave foranea del detalle de solicitud
    @OneToMany(mappedBy="solicitud",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detallesolicitud;

    @Column(length=25)
    private String otrasDonaciones;

    @Column(length=200)
    private String razon;

    @Column(length=5)
    private String prioridad;

    @Column()
    private Date fechaRegistro;

    @Column(length=500)
    private String motivoRechazo;

    @Column(length=1)
    private char estatus;

    public UUID getCod_solicitud() {
        return this.cod_solicitud;
    }

    public void setCod_solicitud(UUID cod_solicitud) {
        this.cod_solicitud = cod_solicitud;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Beneficiario getBeneficiario() {
        return this.beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getMotivoRechazo() {
        return this.motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public String getOtrasDonaciones() {
        return this.otrasDonaciones;
    }

    public void setOtrasDonaciones(String otrasDonaciones) {
        this.otrasDonaciones = otrasDonaciones;
    }

    public String getRazon() {
        return this.razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }


    public Solicitud(UUID cod_solicitud, Empleado empleado, Beneficiario beneficiario, List<DetalleSolicitud> detallesolicitud, String otrasDonaciones, String razon, String prioridad, Date fechaRegistro, String motivoRechazo, char estatus) {
        this.cod_solicitud = cod_solicitud;
        this.empleado = empleado;
        this.beneficiario = beneficiario;
        this.detallesolicitud = detallesolicitud;
        this.otrasDonaciones = otrasDonaciones;
        this.razon = razon;
        this.prioridad = prioridad;
        this.fechaRegistro = fechaRegistro;
        this.motivoRechazo = motivoRechazo;
        this.estatus = estatus;
    }

    public Solicitud(UUID cod_solicitud, Empleado empleado, Beneficiario beneficiario, String otrasDonaciones, String razon, String prioridad, Date fechaRegistro, char estatus) {
        this.cod_solicitud = cod_solicitud;
        this.empleado = empleado;
        this.beneficiario = beneficiario;
        this.otrasDonaciones = otrasDonaciones;
        this.razon = razon;
        this.prioridad = prioridad;
        this.fechaRegistro = fechaRegistro;
        this.estatus = estatus;
    }

    public String obtenerEstatus(){
        String p = "Pendiente";
        String a = "Aprobada";
        String n = "Negada";
        if(this.estatus == 'P'){
            return p;
        }else if(this.estatus == 'A'){
            return a;
        }else{
            return n;
        }
    }

    public double CalcularPresupuesto(UUID codigo){
        int cant = DetalleSolicitud.buscador.contarDetalles(codigo);
        List<DetalleSolicitud> listdet = DetalleSolicitud.buscador.listadoDet(codigo);
        double montototal=0;
        for(int c=0; c<cant; c++){
            DetalleSolicitud ds = listdet.get(c);
            double costo = ds.getCosto();
            montototal += costo;
        }
        return montototal;
    }

    public static final BuscadorSolicitud buscador = new BuscadorSolicitud();
}
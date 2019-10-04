package models;

import java.lang.annotation.Inherited;
import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

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

    @Column(length=5)
    private String prioridad;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length=15)
    private double  montoPresupuesto;

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

    public double getMontoPresupuesto() {
        return this.montoPresupuesto;
    }

    public void setMontoPresupuesto(double montoPresupuesto) {
        this.montoPresupuesto = montoPresupuesto;
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

    public Solicitud(UUID cod_solicitud, Empleado empleado, Beneficiario beneficiario, String prioridad, double montoPresupuesto, Date fechaRegistro, String motivoRechazo, char estatus) {
        this.cod_solicitud = cod_solicitud;
        this.empleado = empleado;
        this.beneficiario = beneficiario;
        this.prioridad = prioridad;
        this.montoPresupuesto = montoPresupuesto;
        this.fechaRegistro = fechaRegistro;
        this.motivoRechazo = motivoRechazo;
        this.estatus = estatus;
    }

}
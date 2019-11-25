package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;
import java.text.SimpleDateFormat;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorSolicitud;
import models.Solicitud;

@Entity
@Table(name="Gobernacion")
public class Gobernacion extends Model {

    @Id
    @Column(length=8)
    public String codGobernacion;

    @Column(length=5)
    public Double partidaAnual;

    @Column(length=1)
    public char estatus;

    public Gobernacion(){
        
    }

    public Gobernacion(String codGobernacion, Double partidaAnual, char estatus) {
        this.codGobernacion = codGobernacion;
        this.partidaAnual = partidaAnual;
        this.estatus = estatus;
    }


    public String getCodGobernacion() {
        return this.codGobernacion;
    }

    public void setCodGobernacion(String codGobernacion) {
        this.codGobernacion = codGobernacion;
    }

    public Double getPartidaAnual() {
        return this.partidaAnual;
    }

    public void setPartidaAnual(Double partidaAnual) {
        this.partidaAnual = partidaAnual;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public double calPresupuesto(String fdesde, String fhasta) throws Exception{
        List<Solicitud> listsol = Solicitud.buscador.listadoTodos();
        double presGenerado=0;
        Date fD = new SimpleDateFormat("dd/MM/yyyy").parse(fdesde);
        Date fH = new SimpleDateFormat("dd/MM/yyyy").parse(fhasta);
            for(int i=0; i<listsol.size(); i++){
                Solicitud sol = listsol.get(i);
                if(sol.getFechaRegistro().after(fD) && sol.getFechaRegistro().before(fH)){
                    presGenerado += sol.CalcularPresupuesto(sol.getCod_solicitud());
                }
            }
        return presGenerado;
    }
}
package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

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

}
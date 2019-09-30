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

    //aqui va la clave foranea de empleado

    //aqui la del beneficiario

    @Column(length=5)
    private String prioridad;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length=15)
    private double costoTotal;
}
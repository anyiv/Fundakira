package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Servicio")
public class Servicio extends Model {

    @Id 
    @Column(length=9)
    @GeneratedValue
    public String codServicio;

    @Constraints.Required(message = "Por favor ingrese la descripcion")
    @Column(columnDefinition = "TEXT")
    public String descripcion;

    @Constraints.Required(message = "Por favor ingrese el tipo")
    @Column(length=10)
    public String tipo;

    @Constraints.Required(message = "Por favor ingrese el costo")
    @Column(length=15)
    public double costo;

    @Column(length=1)
    public char estatus;

    public static Finder<Integer, Servicio> find = new Finder<>(Servicio.class);
}
package models;

import java.util.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlID;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Fundacion")
public class Fundacion extends Model {

    @Id 
    @Column(length=9)
    @GeneratedValue
    public String cod_fundacion;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length = 30 )
    public String nombre;

    @Constraints.Required
    @Column(length = 30)
    public double porcPartida;;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length = 50)
    public String direccion;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    public String correo;

    @Column(length=12)
    public String telefono;

    @Column(length=1)
    public char estatus;

    @OneToMany(mappedBy="fundacion",cascade = CascadeType.ALL)
    public List<Servicio> servicios;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "fundacion") //el que da la clave foranea
    public Empleado empleado;

    public static Finder<Integer, Fundacion> find = new Finder<>(Fundacion.class);
}
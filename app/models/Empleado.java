package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Empleado")
public class Empleado extends Model {

    @Id
    @Column(length=9)
    public String cedula_B;

    // @OnetoOne(mappedBy="cedula_B", cascade = CascadeType.ALL)
    // public Usuario usuarioEmpleado;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length = 30 )
    public String nombre;

    @Constraints.Required(message = "Por favor ingrese el apellido")
    @Column(length = 30)
    public String apellido;

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


    public static Finder<Integer, Empleado> find = new Finder<>(Empleado.class);
    
} 
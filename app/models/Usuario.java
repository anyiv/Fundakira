package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Usuario")
public class Usuario extends Model{

    @Id 
    @Column(length=9)
    public String cedula_E;

    @OneToOne
    @PrimaryKeyJoinColumn(name="cedula_E")
    public Empleado empleado;

    @Constraints.Required(message = "Por favor ingrese la contraseña")
    @Column(length = 30)
    public String contrasenna;

    @Column(length=1)
    public char estatus;

}
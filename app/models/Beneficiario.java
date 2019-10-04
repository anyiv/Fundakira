package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Beneficiario")
public class Beneficiario extends Model {

    @Id
    @Column(length=9,unique=true) 
    private String cedulaB;

    @Constraints.Required(message = "Por favor ingrese el nombre")
    @Column(length=30)
    private String nombre;

    @Constraints.Required(message = "Por favor ingrese el apellido")
    @Column(length=30)
    private String apellido;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length=50)
    private String direccion;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    private String correo;

    @Column(length=12)
    private String telefono;

    @Column(length=1)
    private char estatus;

    //clave foranea para la solicitud
    @OneToMany(mappedBy="beneficiario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Solicitud> solicitud;

    @OneToOne (mappedBy = "beneficiario", fetch = FetchType.LAZY)
    public Usuario_Beneficiario usuario_beneficiario;

    
} 


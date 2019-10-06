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
    private String nombreB;

    @Constraints.Required(message = "Por favor ingrese el apellido")
    @Column(length=30)
    private String apellidoB;

    @Constraints.Required(message = "Por favor ingrese la dirección")
    @Column(length=50)
    private String direccionB;

    @Constraints.Required(message = "Por favor ingrese el correo")
    @Constraints.Email(message = "Por favor verifique el correo")
    private String correoB;

    @Column(length=12)
    private String telefonoB;

    @Column(length=1)
    private char estatus;

    @OneToOne (mappedBy = "beneficiario", fetch = FetchType.LAZY)

    public Usuario_Beneficiario usuario_beneficiario;

    public void setCedulaB(String cedulaB){
        this.cedulaB = cedulaB;
    }

    public String getCedulaB(){
        return this.cedulaB;
    }

    public void setNombreB(String nombreB){
        this.nombreB = nombreB;
    }

    public String getNombreB(){
        return this.nombreB;
    }

    public void setApellidoB(String apellidoB) {
        this.apellidoB = apellidoB;
    }

    public String getApellidoB() {
        return this.apellidoB;        
    }
    
    public void setDireccionB(String direccionB) {
        this.direccionB = direccionB;
    }

    public String getDireccionB(){
        return this.direccionB;
    }

    public void setCorreo(String correoB) {
        this.correoB = correoB;
    }

    public String getCorreoB(){
        return this.correoB;
    }

    public void setTelefono(String telefonoB) {
        this.telefonoB = telefonoB;
    }

    public String getTelefonoB(){
        return this.telefonoB;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public char getEstatus(){
        return this.estatus;
    }

} 



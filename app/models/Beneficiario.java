package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorBeneficiario;

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
    private char estatusB;

    //clave foranea para la solicitud
    @OneToMany(mappedBy="beneficiario",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Solicitud> solicitud;

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

    public void setCorreoB(String correoB) {
        this.correoB = correoB;
    }

    public String getCorreoB(){
        return this.correoB;
    }

    public void setTelefonoB(String telefonoB) {
        this.telefonoB = telefonoB;
    }

    public String getTelefonoB(){
        return this.telefonoB;
    }
    
    public char getEstatusB() {
        return this.estatusB;
    }

    public void setEstatusB(char estatusB) {
        this.estatusB = estatusB;
    }


    public Usuario_Beneficiario getUsuario_beneficiario() {
        return this.usuario_beneficiario;
    }

    public void setUsuario_beneficiario(Usuario_Beneficiario usuario_beneficiario) {
        this.usuario_beneficiario = usuario_beneficiario;
    }



    public static final BuscadorBeneficiario buscador = new BuscadorBeneficiario();
}
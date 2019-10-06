package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;
import buscadores.BuscadorUsuario;

@Entity
@Table(name="Usuario")
public class Usuario extends Model{

    @Id 
    @Column(length=9)
    public UUID codUsuario;

    //la clave foranea del empleado
    @OneToOne (mappedBy = "usuario", fetch = FetchType.EAGER)
    public Usuario_Empleado usuario_empleado;

    //la clave foranea del beneficiario
    @OneToOne (mappedBy = "usuario", fetch = FetchType.EAGER)
    public Usuario_Beneficiario usuario_Beneficiario;

    //la clave foranera del tipo de usuario
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="codTipoUser")
    public TipoUser tipouser;

    @Constraints.Required(message = "Por favor ingrese la contraseña")
    @Column(length = 30)
    public String contrasenna;

    @Column(length=1)
    public char estatus;

    public UUID getCodUsuario() {
        return this.codUsuario;
    }

    public void setCodUsuario(UUID codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Usuario_Empleado getUsuario_empleado() {
        return this.usuario_empleado;
    }

    public void setUsuario_empleado(Usuario_Empleado usuario_empleado) {
        this.usuario_empleado = usuario_empleado;
    }

    public Usuario_Beneficiario getUsuario_Beneficiario() {
        return this.usuario_Beneficiario;
    }

    public void setUsuario_Beneficiario(Usuario_Beneficiario usuario_Beneficiario) {
        this.usuario_Beneficiario = usuario_Beneficiario;
    }

    public TipoUser getTipouser() {
        return this.tipouser;
    }

    public void setTipouser(TipoUser tipouser) {
        this.tipouser = tipouser;
    }

    public String getContrasenna() {
        return this.contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public Usuario(UUID codUsuario, Usuario_Empleado usuario_empleado, Usuario_Beneficiario usuario_Beneficiario, TipoUser tipouser, String contrasenna, char estatus) {
        this.codUsuario = codUsuario;
        this.usuario_empleado = usuario_empleado;
        this.usuario_Beneficiario = usuario_Beneficiario;
        this.tipouser = tipouser;
        this.contrasenna = contrasenna;
        this.estatus = estatus;
    }

    public Usuario() {
    }

    public static final BuscadorUsuario buscador = new BuscadorUsuario();
}
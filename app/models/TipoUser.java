package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="TipoUser")
public class TipoUser extends Model {

    @Id 
    @Column(length=8)
    private String codTipoUser;

    @Column(length=20)
    private String tipoUser;

    @Column(length=1)
    private char estatus;

    //le da la clave foranea a usuario
    @OneToMany(mappedBy="tipouser",cascade = CascadeType.ALL)
    public List<Usuario> usuario;


    public TipoUser(String codTipoUser, String tipoUser, char estatus, List<Usuario> usuario) {
        this.codTipoUser = codTipoUser;
        this.tipoUser = tipoUser;
        this.estatus = estatus;
        this.usuario = usuario;
    }

    public String getCodTipoUser() {
        return this.codTipoUser;
    }

    public void setCodTipoUser(String codTipoUser) {
        this.codTipoUser = codTipoUser;
    }

    public String getTipoUser() {
        return this.tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }

    public List<Usuario> getUsuario() {
        return this.usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }

    
}

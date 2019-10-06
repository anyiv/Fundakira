package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
@Table(name="Usuario_Beneficiario")
public class Usuario_Beneficiario extends Model {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="codUsuario")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cedulaB")
    private Beneficiario beneficiario;

}
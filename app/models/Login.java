package models;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;

public class Login{
    private String cedula;

    private String contrasenna;

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getContrasenna() {
        return this.contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

}
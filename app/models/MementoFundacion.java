package models;

import java.util.UUID;

public class MementoFundacion{
    private UUID cod_fundacion;
    private String nombre;
    private double porcPartida;
    private String direccion;
    private String correo;
    private String telefono;
    private String tipo;
    private char estatus;

    public MementoFundacion() {
    }


    public MementoFundacion(UUID cod_fundacion, String nombre, double porcPartida, String direccion, String correo, String telefono, String tipo, char estatus) {
        this.cod_fundacion = cod_fundacion;
        this.nombre = nombre;
        this.porcPartida = porcPartida;
        this.direccion = direccion;
        this.correo = correo;
        this.telefono = telefono;
        this.tipo = tipo;
        this.estatus = estatus;
    }   

    public UUID getCod_fundacion() {
        return this.cod_fundacion;
    }

    public void setCod_fundacion(UUID cod_fundacion) {
        this.cod_fundacion = cod_fundacion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcPartida() {
        return this.porcPartida;
    }

    public void setPorcPartida(double porcPartida) {
        this.porcPartida = porcPartida;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public char getEstatus() {
        return this.estatus;
    }

    public void setEstatus(char estatus) {
        this.estatus = estatus;
    }


}
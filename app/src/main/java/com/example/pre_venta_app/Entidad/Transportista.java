package com.example.pre_venta_app.Entidad;

import java.io.Serializable;

public class Transportista implements Serializable {

    String cod_transportista;
    String des_transportista;
    String direccion;
    String telefono;
    String correo;
    String placa;

    public String getCod_transportista() {
        return cod_transportista;
    }

    public void setCod_transportista(String cod_transportista) {
        this.cod_transportista = cod_transportista;
    }

    public String getDes_transportista() {
        return des_transportista;
    }

    public void setDes_transportista(String des_transportista) {
        this.des_transportista = des_transportista;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Transportista() {
    }

    public Transportista(String cod_transportista, String des_transportista, String direccion, String telefono, String correo, String placa) {
        this.cod_transportista = cod_transportista;
        this.des_transportista = des_transportista;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.placa = placa;
    }
}

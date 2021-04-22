package com.example.pre_venta_app.Entidad;

import java.io.Serializable;

public class Cliente implements Serializable {
    String cod_cliente;
    String ruc;
    String des_cliente;
    String direccion;
    String telefono;
    String correo;
    String contacto;

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDes_cliente() {
        return des_cliente;
    }

    public void setDes_cliente(String des_cliente) {
        this.des_cliente = des_cliente;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Cliente() {
    }

    public Cliente(String cod_cliente, String ruc, String des_cliente, String direccion, String telefono, String correo, String contacto) {
        this.cod_cliente = cod_cliente;
        this.ruc = ruc;
        this.des_cliente = des_cliente;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.contacto = contacto;
    }
}

package com.example.pre_venta_app.Entidad;

import java.io.Serializable;

public class Sucursal implements Serializable {

    String cod_cliente;
    String desc_cliente;
    String cod_sucursal;
    String des_sucursal;
    String direccion;

    public String getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(String cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public String getDesc_cliente() {
        return desc_cliente;
    }

    public void setDesc_cliente(String desc_cliente) {
        this.desc_cliente = desc_cliente;
    }

    public String getCod_sucursal() {
        return cod_sucursal;
    }

    public void setCod_sucursal(String cod_sucursal) {
        this.cod_sucursal = cod_sucursal;
    }

    public String getDes_sucursal() {
        return des_sucursal;
    }

    public void setDes_sucursal(String des_sucursal) {
        this.des_sucursal = des_sucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Sucursal() {
    }

    public Sucursal(String cod_cliente, String desc_cliente, String cod_sucursal, String des_sucursal, String direccion) {
        this.cod_cliente = cod_cliente;
        this.desc_cliente = desc_cliente;
        this.cod_sucursal = cod_sucursal;
        this.des_sucursal = des_sucursal;
        this.direccion = direccion;
    }
}

package com.example.pre_venta_app.Entidad;

public class Articulo {

    String cod_articulo;
    String desc_articulo;
    String cod_und;
    String und_medida;
    double saldo;
    double precio;

    public String getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public String getDesc_articulo() {
        return desc_articulo;
    }

    public void setDesc_articulo(String desc_articulo) {
        this.desc_articulo = desc_articulo;
    }

    public String getCod_und() {
        return cod_und;
    }

    public void setCod_und(String cod_und) {
        this.cod_und = cod_und;
    }

    public String getUnd_medida() {
        return und_medida;
    }

    public void setUnd_medida(String und_medida) {
        this.und_medida = und_medida;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Articulo() {
    }

    public Articulo(String cod_articulo, String desc_articulo, String cod_und, String und_medida, double saldo, double precio) {
        this.cod_articulo = cod_articulo;
        this.desc_articulo = desc_articulo;
        this.cod_und = cod_und;
        this.und_medida = und_medida;
        this.saldo = saldo;
        this.precio = precio;
    }
}

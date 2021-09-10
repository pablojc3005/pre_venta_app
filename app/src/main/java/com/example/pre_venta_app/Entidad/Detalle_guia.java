package com.example.pre_venta_app.Entidad;

public class Detalle_guia {

    String cod_registro;
    String secuencia;
    String cod_articulo;
    String articulo;
    String cantidad;
    String precio;
    String igv;
    String sub_total;
    String total;

    public String getCod_registro() {
        return cod_registro;
    }

    public void setCod_registro(String cod_registro) {
        this.cod_registro = cod_registro;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCod_articulo() {
        return cod_articulo;
    }

    public void setCod_articulo(String cod_articulo) {
        this.cod_articulo = cod_articulo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIgv() {
        return igv;
    }

    public void setIgv(String igv) {
        this.igv = igv;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Detalle_guia() {
    }

    public Detalle_guia(String cod_articulo, String articulo, String cantidad, String precio) {
        this.cod_articulo = cod_articulo;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Detalle_guia(String secuencia, String cod_articulo, String articulo, String cantidad, String precio) {
        this.secuencia = secuencia;
        this.cod_articulo = cod_articulo;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}

package com.example.pre_venta_app.Entidad;

import java.io.Serializable;
import java.util.List;

public class Guia implements Serializable {
    int cod_registro;
    String cod_almacen;
    String tipo_movimiento;
    String fecha_registro ;
    String Codcliente ;
    String ruc ;
    String Descliente ;
    String cod_moneda;
    String Codtransportis;
    String Destransportis;
    String CodFormaPago;
    Double sub_total;
    Double igv;
    Double total;
    String status_registro;
    String tipo_guia_imp;

    List<Detalle_guia> lista_detalle;

    public int getCod_registro() {
        return cod_registro;
    }

    public void setCod_registro(int cod_registro) {
        this.cod_registro = cod_registro;
    }

    public String getCod_almacen() {
        return cod_almacen;
    }

    public void setCod_almacen(String cod_almacen) {
        this.cod_almacen = cod_almacen;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public String getCodcliente() {
        return Codcliente;
    }

    public void setCodcliente(String codcliente) {
        Codcliente = codcliente;
    }

    public String getCod_moneda() {
        return cod_moneda;
    }

    public void setCod_moneda(String cod_moneda) {
        this.cod_moneda = cod_moneda;
    }

    public String getCodtransportis() {
        return Codtransportis;
    }

    public void setCodtransportis(String codtransportis) {
        Codtransportis = codtransportis;
    }

    public String getCodFormaPago() {
        return CodFormaPago;
    }

    public void setCodFormaPago(String codFormaPago) {
        CodFormaPago = codFormaPago;
    }

    public Double getSub_total() {
        return sub_total;
    }

    public void setSub_total(Double sub_total) {
        this.sub_total = sub_total;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus_registro() {
        return status_registro;
    }

    public void setStatus_registro(String status_registro) {
        this.status_registro = status_registro;
    }

    public String getTipo_guia_imp() {
        return tipo_guia_imp;
    }

    public void setTipo_guia_imp(String tipo_guia_imp) {
        this.tipo_guia_imp = tipo_guia_imp;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDescliente() {
        return Descliente;
    }

    public void setDescliente(String descliente) {
        Descliente = descliente;
    }

    public String getDestransportis() {
        return Destransportis;
    }

    public void setDestransportis(String destransportis) {
        Destransportis = destransportis;
    }

    public List<Detalle_guia> getLista_detalle() {
        return lista_detalle;
    }

    public void setLista_detalle(List<Detalle_guia> lista_detalle) {
        this.lista_detalle = lista_detalle;
    }

    public Guia() {
    }
    public Guia(int cod_registro, String cod_almacen, String tipo_movimiento, String codcliente, String cod_moneda, String codtransportis, String codFormaPago, Double sub_total, Double igv, Double total, String status_registro, String tipo_guia_imp) {
        this.cod_registro = cod_registro;
        this.cod_almacen = cod_almacen;
        this.tipo_movimiento = tipo_movimiento;
        this.Codcliente = codcliente;
        this.cod_moneda = cod_moneda;
        this.Codtransportis = codtransportis;
        this.CodFormaPago = codFormaPago;
        this.sub_total = sub_total;
        this.igv = igv;
        this.total = total;
        this.status_registro = status_registro;
        this.tipo_guia_imp = tipo_guia_imp;
    }

    public Guia(int cod_registro, String fecha_registro, String codcliente, String ruc, String descliente, String codtransportis, String destransportis, String codFormaPago, String cod_moneda, Double total) {
        this.cod_registro = cod_registro;
        this.fecha_registro = fecha_registro;
        this.Codcliente = codcliente;
        this.ruc = ruc;
        this.Descliente = descliente;
        this.Codtransportis = codtransportis;
        this.Destransportis = destransportis;
        this.CodFormaPago = codFormaPago;
        this.cod_moneda = cod_moneda;
        this.total = total;
    }
}

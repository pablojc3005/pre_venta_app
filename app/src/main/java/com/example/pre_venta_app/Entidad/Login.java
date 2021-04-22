package com.example.pre_venta_app.Entidad;

public class Login {
    private String nombre;
    private String cod_usu;
    private String clave;
    private String flag_estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFlag_estado() {
        return flag_estado;
    }

    public void setFlag_estado(String flag_estado) {
        this.flag_estado = flag_estado;
    }

    public Login() {
    }

    public Login(String nombre, String cod_usu, String clave, String flag_estado) {
        this.nombre = nombre;
        this.cod_usu = cod_usu;
        this.clave = clave;
        this.flag_estado = flag_estado;
    }
}

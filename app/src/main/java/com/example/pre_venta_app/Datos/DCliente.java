package com.example.pre_venta_app.Datos;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.pre_venta_app.Entidad.Cliente;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;


public class DCliente {

    public static ArrayList<Cliente> Lista_clientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = " select codcliente, ruccliente, descliente, direntregacli, telef01cli, correocli, contactocli from clientes \n" +
                     " where status_registro = 'V';";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Cliente(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            Log.e("lista clientes : ", lista.toString());
        }
        catch (java.sql.SQLException e)
        {
            Log.e("Excepcion 01", e.toString());
        }
        catch (Exception e) {
            Log.e("Excepcion 02", e.toString());
        }
        return  lista;
    }

    public static String Insertar_cliente(Cliente c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_cliente_insert(?,?,?,?,?,?,?,?)}");
            pst.setString(1, c.getCod_cliente());
            Log.e("getCod_cliente ", c.getCod_cliente());
            pst.setString(2, c.getRuc());
            Log.e("getRuc ", c.getRuc());
            pst.setString(3, c.getDes_cliente());
            Log.e("getDes_cliente ", c.getDes_cliente());
            pst.setString(4, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.setString(5, c.getTelefono());
            Log.e("getTelefono ", c.getTelefono());
            pst.setString(6, c.getCorreo());
            Log.e("getCorreo ", c.getCorreo());
            pst.setString(7, c.getContacto());
            Log.e("getContacto ", c.getContacto());
            pst.registerOutParameter(8, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(8);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }

    public static String Actualizar_cliente(Cliente c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_cliente_update(?,?,?,?,?,?,?,?)}");
            pst.setString(1, c.getCod_cliente());
            Log.e("getCod_cliente ", c.getCod_cliente());
            pst.setString(2, c.getRuc());
            Log.e("getRuc ", c.getRuc());
            pst.setString(3, c.getDes_cliente());
            Log.e("getDes_cliente ", c.getDes_cliente());
            pst.setString(4, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.setString(5, c.getTelefono());
            Log.e("getTelefono ", c.getTelefono());
            pst.setString(6, c.getCorreo());
            Log.e("getCorreo ", c.getCorreo());
            pst.setString(7, c.getContacto());
            Log.e("getContacto ", c.getContacto());
            pst.registerOutParameter(8, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(8);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }
}

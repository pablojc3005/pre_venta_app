package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Cliente;
import com.example.pre_venta_app.Entidad.Transportista;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class DTransportista {

    public static ArrayList<Transportista> Lista_transportista() {
        ArrayList<Transportista> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = " select CodTransportis, DesTransportis, DirTransportis, Telef01Transp, Correotransp, PlacaTransporte from Trasnportistas \n" +
                " where status_registro = 'V';";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Transportista(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            Log.e("lista transportista : ", lista.toString());
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

    public static String Insertar_transportista(Transportista c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_transportista_insert(?,?,?,?,?,?,?)}");
            pst.setString(1, c.getCod_transportista());
            Log.e("getCod_transportista ", c.getCod_transportista());
            pst.setString(2, c.getDes_transportista());
            Log.e("getDes_transportista ", c.getDes_transportista());
            pst.setString(3, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.setString(4, c.getTelefono());
            Log.e("getTelefono ", c.getTelefono());
            pst.setString(5, c.getCorreo());
            Log.e("getCorreo ", c.getCorreo());
            pst.setString(6, c.getPlaca());
            Log.e("getPlaca ", c.getPlaca());
            pst.registerOutParameter(7, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(7);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }

    public static String Actualizar_transportista(Transportista c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_transportista_update(?,?,?,?,?,?,?)}");
            pst.setString(1, c.getCod_transportista());
            Log.e("getCod_transportista ", c.getCod_transportista());
            pst.setString(2, c.getDes_transportista());
            Log.e("getDes_transportista ", c.getDes_transportista());
            pst.setString(3, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.setString(4, c.getTelefono());
            Log.e("getTelefono ", c.getTelefono());
            pst.setString(5, c.getCorreo());
            Log.e("getCorreo ", c.getCorreo());
            pst.setString(6, c.getPlaca());
            Log.e("getPlaca ", c.getPlaca());
            pst.registerOutParameter(7, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(7);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }
}

package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Sucursal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class DSucursal {

    public static ArrayList<Sucursal> Lista_sucursales() {
        ArrayList<Sucursal> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = " select s.CodCliente, C.DesCliente, s.CodSucursal, s.DesSucursal, s.DirentregaSuc from SucursalCliente s \n" +
                     " inner join Clientes c on S.CodCliente = C.CodCliente \n" +
                     " where s.status_registro = 'V';";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Sucursal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            Log.e("lista sucursales : ", lista.toString());
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

    public static String Insertar_sucursal(Sucursal c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_sucursal_insert(?,?,?,?,?)}");
            pst.setString(1, c.getCod_cliente());
            Log.e("getCod_cliente ", c.getCod_cliente());
            pst.setString(2, c.getCod_sucursal());
            Log.e("getCod_sucursal ", c.getCod_sucursal());
            pst.setString(3, c.getDes_sucursal());
            Log.e("getDes_sucursal ", c.getDes_sucursal());
            pst.setString(4, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.registerOutParameter(5, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(5);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }

    public static String Actualizar_sucursal(Sucursal c) {
        String rpta = null;
        Connection cn = null;
        CallableStatement pst = null;
        ResultSet rs = null;
        try {
            cn = Conexion.Conectar();
            pst = cn.prepareCall("{call usp_sucursal_update(?,?,?,?,?)}");
            pst.setString(1, c.getCod_cliente());
            Log.e("getCod_cliente ", c.getCod_cliente());
            pst.setString(2, c.getCod_sucursal());
            Log.e("getCod_sucursal ", c.getCod_sucursal());
            pst.setString(3, c.getDes_sucursal());
            Log.e("getDes_sucursal ", c.getDes_sucursal());
            pst.setString(4, c.getDireccion());
            Log.e("getDireccion ", c.getDireccion());
            pst.registerOutParameter(5, Types.VARCHAR);
            pst.execute();
            rpta = pst.getString(5);
            Log.e("rpta ", rpta);
            cn.close();

        } catch (Exception ex) {
            Log.e("Error", ex.getMessage());
        }
        return rpta;
    }
}

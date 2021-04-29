package com.example.pre_venta_app.Datos;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DGuia {

    public static ArrayList<String> Lista_forma_pago() {
        ArrayList<String> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = "select CodForpago, DesForpago from FormaPago where tipo_doc = 'F'";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(rs.getString(2));
            }
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
}

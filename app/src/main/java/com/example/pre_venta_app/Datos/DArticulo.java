package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DArticulo {

    public static ArrayList<Articulo> Lista_articulos() {
        ArrayList<Articulo> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = " SELECT Kardex.CodArticulo,  Articulos.DesArticulo, Articulos.UnimedArt, \n" +
                     " UnidadMedida.DesUmedida, Kardex.Saldo, Kardex.Precio  \n" +
                     " FROM Articulos, UnidadMedida, Kardex, Almacen  \n" +
                     " WHERE UnidadMedida.CodUmedida = Articulos.UnimedArt and\n" +
                     " ( Articulos.CodArticulo = Kardex.CodArticulo ) and  \n" +
                     " ( Kardex.Cod_Almacen = Almacen.Cod_almacen ) and  \n" +
                     " ( ( Kardex.Sec = 9999999 ) AND  Kardex.Cod_Almacen = 1 );";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Articulo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getDouble(6)));
            }
            Log.e("lista articulos : ", lista.toString());
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
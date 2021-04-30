package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Detalle_guia;
import com.example.pre_venta_app.Entidad.Guia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DGuia {

    public static boolean Guardar_guia(Guia v)
    {
        Integer r = 0, r2 = 0, maxId = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement ps2 = null;
        PreparedStatement pst3 = null;
        ResultSet rs;
        try{
            cn = Conexion.Conectar();
            String sql = "{call usp_insertar_guia_cab(?,?,?,?,?,?)}";
            pst = cn.prepareCall(sql);
            pst.setString(1, v.getCodcliente());
            System.out.println("Codcliente "+ v.getCodcliente());
            pst.setString(2, v.getCodtransportis());
            System.out.println("Codtransportis "+ v.getCodtransportis());
            pst.setString(3, v.getCodFormaPago());
            System.out.println("CodFormaPago "+ v.getCodFormaPago());
            pst.setDouble(4, v.getSub_total());
            System.out.println("Sub_total "+ v.getSub_total());
            pst.setDouble(5, v.getIgv());
            System.out.println("Igv "+ v.getIgv());
            pst.setDouble(6, v.getTotal());
            System.out.println("Total "+ v.getTotal());
            r = pst.executeUpdate() ;
            r = 1;

            String sql2 = "select max(cod_registro) from Guias_Remision_Cab;";
            ps2 = cn.prepareStatement(sql2);
            rs = ps2.executeQuery();
            rs.next();
            maxId = rs.getInt(1);
            System.out.println("maxId " + String.valueOf(maxId));

            String sql3 = "{call usp_insertar_guia_det(?,?,?,?,?)}";
            pst3 = cn.prepareCall(sql3);
            for ( Detalle_guia d : v.getLista_detalle())
            {
                pst3.setString(1, String.valueOf(maxId));
                System.out.println("ID "+ String.valueOf(maxId));
                pst3.setString(2, d.getSecuencia());
                System.out.println("Secuencia "+ d.getSecuencia());
                pst3.setString(3, d.getCod_articulo());
                System.out.println("Cod_articulo "+ d.getCod_articulo());
                /*pst3.setString(4, d.getArticulo());
                System.out.println("Desc_articulo "+ d.getArticulo());*/
                pst3.setString(4, d.getCantidad());
                System.out.println("Cantidad "+ d.getCantidad());
                pst3.setString(5, d.getPrecio());
                System.out.println("Precio "+ d.getPrecio());
                r2 = pst3.executeUpdate();
            }
            r2 = 1;
            cn.close();
        } catch (Exception e) {
            System.out.print("Error waxx "+ e.toString());
        }
        return r == 1 && r2 == 1 ? true:false;
    }

    public static ArrayList<String> Lista_forma_pago() {
        ArrayList<String> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = "select CodForpago, DesForpago from FormaPago where tipo_doc = 'F'";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(rs.getString(1)+" - "+rs.getString(2));
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

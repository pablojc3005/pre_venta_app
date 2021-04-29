package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Guia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DGuia {

    /*public static boolean Guardar_guia(Guia v)
    {
        Integer r = 0, r2 = 0, maxId = 0;
        Connection cn = null;
        PreparedStatement pst = null;
        PreparedStatement ps2 = null;
        PreparedStatement pst3 = null;
        ResultSet rs;
        try{
            cn = Conexion.Conectar();
            String sql = "{call usp_inserta_venta_pre(?,?,?,?,?,?,?)}";
            pst = cn.prepareCall(sql);
            pst.setString(1, v.getCod_cliente());
            System.out.println("cod_cliente "+ v.getCod_cliente());
            pst.setString(2, v.getNom_cliente());
            System.out.println("nom_cliente "+ v.getNom_cliente());
            pst.setInt(3, v.getCod_vendedor());
            System.out.println("cod_vendedor "+ v.getCod_vendedor());
            pst.setString(4, v.getTipo_despacho());
            System.out.println("tipo_despacho "+ v.getTipo_despacho());
            pst.setString(5, v.getTipo_pedido());
            System.out.println("tipo_pedido "+ v.getTipo_pedido());
            pst.setString(6, v.getTipo_doc());
            System.out.println("tipo_doc "+ v.getTipo_doc());
            pst.setInt(7, v.getEnvio_email());
            System.out.println("envio_email "+ v.getEnvio_email());
            r = pst.executeUpdate() ;
            r = 1;

            String sql2 = "select max(docentry) from odrf;";
            ps2 = cn.prepareStatement(sql2);
            rs = ps2.executeQuery();
            rs.next();
            maxId = rs.getInt(1);
            System.out.println("maxId " + String.valueOf(maxId));

            String sql3 = "{call usp_inserta_venta_det_pre(?,?,?,?,?,?)}";
            pst3 = cn.prepareCall(sql3);
            for ( Detalle_venta d : v.getLista_detalle())
            {
                pst3.setString(1, String.valueOf(maxId));
                System.out.println("ID "+ String.valueOf(maxId));
                pst3.setString(2, d.getItem());
                System.out.println("Item "+ d.getItem());
                pst3.setString(3, d.getCod_articulo());
                System.out.println("Cod_articulo "+ d.getCod_articulo());
                pst3.setString(4, d.getDesc_articulo());
                System.out.println("Desc_articulo "+ d.getDesc_articulo());
                pst3.setString(5, d.getCantidad());
                System.out.println("Cantidad "+ d.getCantidad());
                pst3.setString(6, d.getPrecio());
                System.out.println("Precio "+ d.getPrecio());
                r2 = pst3.executeUpdate();
            }
            r2 = 1;
            cn.close();
        } catch (Exception e) {
            System.out.print("Error waxx "+ e.toString());
        }
        return r == 1 && r2 == 1 ? true:false;
    }*/

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

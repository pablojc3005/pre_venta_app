package com.example.pre_venta_app.Datos;

import android.util.Log;

import com.example.pre_venta_app.Entidad.Cliente;
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

    public static Integer Ultimo_registro(){
        int result = 0;
        String sql = "select max(cod_registro) from Guias_Remision_Cab;";

        ResultSet rs;
        try {
            Connection cn = Conexion.Conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            //pst.setString(1,cod_articulo);
            rs = pst.executeQuery();
            rs.next();
            result = rs.getInt(1);
            cn.close();
        }
        catch (java.sql.SQLException e)
        {
            System.out.println("Excepcion 01 - CapturaId "+ e.toString());
        }
        catch (Exception e) {
            System.out.println("Excepcion 02 - CapturaId "+ e.toString());
        }
        return result;
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

    public static ArrayList<Guia> Lista_presupuesto() {
        ArrayList<Guia> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = "select g.Cod_Registro, convert(varchar, g.Fecha_guia, 105) as fecha, g.Codcliente, c.RucCliente, c.DesCliente, \n" +
                     "g.Codtransportis, t.DesTransportis, g.CodFormaPago, m.Des_moneda_abrev,  g.total \n" +
                     "from Guias_Remision_Cab g \n" +
                     "inner join Clientes c on g.Codcliente = c.CodCliente\n" +
                     "inner join Trasnportistas t on g.Codtransportis = t.CodTransportis\n" +
                     "inner join Monedas m on g.Cod_moneda = m.Cod_moneda \n" +
                     "where year(g.Fecha_guia) = year(getdate())\n" +
                     "order by g.Fecha_guia desc;";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Guia(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDouble(10)));
            }
            Log.e("lista presupuesto : ", lista.toString());
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

    public static ArrayList<Detalle_guia> Lista_presupuesto_det(int cod_registro) {
        ArrayList<Detalle_guia> lista = new ArrayList<>();
        Connection cn = Conexion.Conectar();
        String sql = "select g.Secuencia, g.CodArticulo, a.DesArticulo, g.Cantidad, g.Precio  from Guias_Remision_Det g\n" +
                "inner join Articulos a on g.CodArticulo = a.CodArticulo\n" +
                "where g.Cod_registro = ?;";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, cod_registro);
            System.out.println("cod_registro "+ cod_registro);
            ResultSet rs = pst.executeQuery();
            while (rs.next())
            {
                lista.add(new Detalle_guia(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            Log.e("lista presupuesto : ", lista.toString());
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

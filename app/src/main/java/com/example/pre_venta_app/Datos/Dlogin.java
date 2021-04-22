package com.example.pre_venta_app.Datos;

import com.example.pre_venta_app.Entidad.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dlogin {

    public static String AccesoLogin(Login l) {

        String r = "";

        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            cn = Conexion.Conectar();

            String sql1 = "select count(login_windows) from Usuario "+
                    " where login_windows = ? and "+
                    " status_registro = 'V'";

            pst = cn.prepareStatement(sql1);
            pst.setString(1, l.getCod_usu());
            rs = pst.executeQuery();

            while (rs.next()) {
                r = String.valueOf(rs.getInt(1));
            }

            if (r.equals("0")) {
                r = "No Existe El Usuario : "+l.getCod_usu();
                return r;
            } else {
                String sql2 = " select count(password) from Usuario " +
                        " where login_windows = ? and " +
                        " status_registro = 'V'";

                pst = cn.prepareStatement(sql2);
                pst.setString(1, l.getCod_usu());

                rs = pst.executeQuery();
                while (rs.next()) {
                    r = String.valueOf(rs.getInt(1));
                }

                if (r.equals("0")) {
                    r = "No Tiene una Contraseña Asignada Comunicarse con Sistemas";
                    return r;
                }

                String sql3 = " select count(password) from Usuario " +
                        " where login_windows = ? and " +
                        " password = ? and " +
                        " status_registro = 'V'";

                pst = cn.prepareStatement(sql3);
                pst.setString(1, l.getCod_usu());
                pst.setString(2, l.getClave());

                rs = pst.executeQuery();
                while (rs.next()){
                    r = String.valueOf(rs.getInt(1));
                }
                if (r.equals("0")) {
                    r = "La Contraseña no Es Correcta vuleva a Intarlo...";
                    return r;
                }
            }
            cn.close();

        } catch (Exception ex) {
            System.out.println("java.sql.SQLException "+ ex.toString());
        }
        return r;
    }

    public static Integer Validar2(Login ent){
        int result = 0;
        String sql = "SELECT cod_usuario, primer_nombre FROM Usuario where login_windows = ? AND password = ? and status_registro ='V';";
        ResultSet rs;
        try {
            Connection cn = Conexion.Conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            System.out.println("sql "+ sql);
            pst.setString(1, ent.getCod_usu());
            System.out.println("usuario "+ ent.getCod_usu());
            pst.setString(2, ent.getClave());
            System.out.println("contraseña "+ ent.getClave());
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

}

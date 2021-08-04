package com.example.pre_venta_app.Datos;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection cn;

    public static Connection Conectar() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //cn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.3.20;databaseName=PRUEBA_PB_20200827;user=sa;password=Passw0rd;");
            cn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.10.150;databaseName=BD_Claridad;user=sa;password=123;integratedSecurity=true;");
            //cn = DriverManager.getConnection("jdbc:jtds:sqlserver://servidorsqlleonardo.database.windows.net:1433;databaseName=BD_Claridad;user=administrador@servidorsqlleonardo;password=Lima.2020;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            System.out.println("CONEXION EXISTOSA :) " + cn.toString());
            } catch (Exception ex) {
            System.out.println("ERROR CONEXION :( "+ ex.getMessage().toString());
        }
        return cn;
    }
}

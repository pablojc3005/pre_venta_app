package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pre_venta_app.Datos.Conexion;
import com.example.pre_venta_app.R;

public class MainActivity extends AppCompatActivity {
    Context context;
    Button btn_conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_conexion = (Button) findViewById(R.id.btnconexion);
        context = this;
        btn_conexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"CONEXION EXITOSA" + Conexion.Conectar().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

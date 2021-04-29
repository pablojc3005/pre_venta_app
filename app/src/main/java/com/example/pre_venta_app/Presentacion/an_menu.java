package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.example.pre_venta_app.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class an_menu extends AppCompatActivity {

    CardView cv_stock, cv_presupuesto, cv_agencia, cv_cliente, cv_usuario, cv_salir;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        cv_stock       = (CardView) findViewById(R.id.cvstock);
        cv_presupuesto = (CardView) findViewById(R.id.cvpresupuesto);
        cv_agencia     = (CardView) findViewById(R.id.cvagencia_tra);
        cv_cliente     = (CardView) findViewById(R.id.cvcliente);
        cv_usuario     = (CardView) findViewById(R.id.cvsucursal);
        cv_salir       = (CardView) findViewById(R.id.cvsalir);

        cv_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_productos.class);
                startActivity(f);
            }
        });

        cv_presupuesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_presupuesto.class);
                startActivity(f);
            }
        });

        cv_agencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_transportita.class);
                startActivity(f);
            }
        });

        cv_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_clientes.class);
                startActivity(f);
            }
        });

        cv_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cv_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("¿ESTA SEGURO DE QUE DESEA SALIR?");
                dialog.setConfirmText("SI");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        System.exit(0);
                        sDialog.dismissWithAnimation();
                    }
                }).setCancelButton("NO", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                }).show();
            }
        });
    }
}

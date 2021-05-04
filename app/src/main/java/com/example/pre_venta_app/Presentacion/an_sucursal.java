package com.example.pre_venta_app.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pre_venta_app.Adapter.adp_lista_sucursal;
import com.example.pre_venta_app.Datos.DSucursal;
import com.example.pre_venta_app.Entidad.Sucursal;
import com.example.pre_venta_app.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class an_sucursal extends AppCompatActivity {

    public static TextView tvcod_cliente, tvdes_cliente, tvcod_sucursal, tvdes_sucursal, tvdireccion;
    public static boolean est_seleccion_cliente2 = false;
    private Sucursal su;
    Button btnguardar, btncancelar, btnbuscar_cliente;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_sucursal);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        tvcod_cliente = (TextView) findViewById(R.id.txtcod_cliente);
        tvdes_cliente = (TextView) findViewById(R.id.txtnom_cliente);
        tvdireccion = (TextView) findViewById(R.id.txtdireccion);
        tvcod_sucursal = (TextView) findViewById(R.id.txtcod_sucursal);
        tvdes_sucursal = (TextView) findViewById(R.id.txtsucursal);

        btncancelar = (Button) findViewById(R.id.btncanelar_suc);
        btnguardar = (Button) findViewById(R.id.btnguardar);
        btnbuscar_cliente = (Button) findViewById(R.id.btnbuscar_cliente);

        if (adp_lista_sucursal.flag_modificar) {
            su = (Sucursal) getIntent().getExtras().getSerializable("itemDetail");
            tvcod_cliente.setText(su.getCod_cliente());
            tvdes_cliente.setText(su.getDesc_cliente());
            tvcod_sucursal.setText(su.getCod_sucursal());
            tvdes_sucursal.setText(su.getDes_sucursal());
            tvdireccion.setText(su.getDireccion());
        }

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                adp_lista_sucursal.flag_modificar = false;
            }
        });

        btnbuscar_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                est_seleccion_cliente2 = true;
                Intent f = new Intent(context, an_lista_clientes.class);
                startActivity(f);
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Sucursal s = new Sucursal();

                if (tvcod_sucursal.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el CODIGO DE SUCURSAL").show();
                    tvcod_sucursal.requestFocus();
                    return;
                }

                if (tvdes_sucursal.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el NOMBRE DE SUCURSAL").show();
                    tvdes_sucursal.requestFocus();
                    return;
                }

                if (tvcod_cliente.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("SELECCIONE EL CLIENTE").show();
                    tvcod_cliente.requestFocus();
                    return;
                }

                s.setCod_cliente(tvcod_cliente.getText().toString().trim());
                s.setDesc_cliente(tvdes_cliente.getText().toString().trim());
                s.setCod_sucursal(tvcod_sucursal.getText().toString().trim());
                s.setDes_sucursal(tvdes_sucursal.getText().toString().trim());
                s.setDireccion(tvdireccion.getText().toString().trim());

                Log.e("flag_modificar", String.valueOf(adp_lista_sucursal.flag_modificar));

                if (adp_lista_sucursal.flag_modificar){
                    if (DSucursal.Actualizar_sucursal(s).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN ACTUALIZADO LOS DATOS DE LA SUCURSAL CORRECTAMENTE...").show();
                        an_lista_sucursal.adapter.notifyDataSetChanged();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DSucursal.Actualizar_sucursal(s)).show();
                    }
                }else{
                    if (DSucursal.Insertar_sucursal(s).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN GUARDADO LOS DATOS DE LA SUCURSAL CORRECTAMENTE...").show();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DSucursal.Insertar_sucursal (s)).show();
                    }
                }
            }
        });
    }

    void Limpar()
    {
        tvcod_cliente.setText("");
        tvdes_cliente.setText("");
        tvcod_sucursal.setText("");
        tvdireccion.setText("");
        tvdes_sucursal.setText("");
    }
}

package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pre_venta_app.Adapter.adp_lista_clientes;
import com.example.pre_venta_app.Datos.DCliente;
import com.example.pre_venta_app.Entidad.Cliente;
import com.example.pre_venta_app.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class an_cliente extends AppCompatActivity {

    public static TextView tvcod_cliente, tvruc, tv_razon_social, tvdireccion, tvtelefono, tvcorreo, tvcontacto ;
    private Cliente cli;
    Button btnguardar, btncancelar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_cliente);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        tvcod_cliente = (TextView) findViewById(R.id.txtcod_cliente);
        tvruc = (TextView) findViewById(R.id.txtruc);
        tv_razon_social = (TextView) findViewById(R.id.txtrazon_social);
        tvdireccion = (TextView) findViewById(R.id.txtdireccion);
        tvtelefono = (TextView) findViewById(R.id.txtcod_cliente);
        tvcorreo = (TextView) findViewById(R.id.txtcorreo);
        tvcontacto = (TextView) findViewById(R.id.txtcontacto);

        btnguardar = (Button) findViewById(R.id.btnguardar);
        btncancelar = (Button) findViewById(R.id.btncanelar_cli);

        if (adp_lista_clientes.flag_modificar)
        {
            cli = (Cliente) getIntent().getExtras().getSerializable("itemDetail");
            tvcod_cliente.setText(cli.getCod_cliente());
            tvruc.setText(cli.getRuc());
            tv_razon_social.setText(cli.getDes_cliente());
            tvdireccion.setText(cli.getDireccion());
            tvtelefono.setText(cli.getTelefono());
            tvcorreo.setText(cli.getCorreo());
            tvcontacto.setText(cli.getContacto());
        }

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                adp_lista_clientes.flag_modificar = false;
            }
        });



        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cliente cl = new Cliente();

                if (tvcod_cliente.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el CODIGO CLIENTE").show();
                    tvcod_cliente.requestFocus();
                    return;
                }

                if (tvruc.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el RUC").show();
                    tvruc.requestFocus();
                    return;
                }

                if (tv_razon_social.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese la RAZON SOCIAL").show();
                    tv_razon_social.requestFocus();
                    return;
                }
                cl.setCod_cliente(tvcod_cliente.getText().toString().trim());
                cl.setRuc(tvruc.getText().toString().trim());
                cl.setDes_cliente(tv_razon_social.getText().toString().trim());
                cl.setDireccion(tvdireccion.getText().toString().trim());
                cl.setTelefono(tvtelefono.getText().toString().trim());
                cl.setCorreo(tvcorreo.getText().toString().trim());
                cl.setContacto(tvcontacto.getText().toString().trim());

                Log.e("flag_modificar", String.valueOf(adp_lista_clientes.flag_modificar));

                if (adp_lista_clientes.flag_modificar){
                    if (DCliente.Actualizar_cliente(cl).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN ACTUALIZADO LOS DATOS DEL CLIENTE CORRECTAMENTE...").show();
                        an_lista_clientes.adapter.notifyDataSetChanged();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DCliente.Actualizar_cliente(cl)).show();
                    }
                }else{
                    if (DCliente.Insertar_cliente(cl).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN GUARDADO LOS DATOS DEL CLIENTE CORRECTAMENTE...").show();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DCliente.Insertar_cliente(cl)).show();
                    }
                }
            }
        });
    }

    void Limpar()
    {
        tvcod_cliente.setText("");
        tvruc.setText("");
        tv_razon_social.setText("");
        tvdireccion.setText("");
        tvtelefono.setText("");
        tvcorreo.setText("");
        tvcontacto.setText("");
    }
}

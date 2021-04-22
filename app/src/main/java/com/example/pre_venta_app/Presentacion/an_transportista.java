package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.pre_venta_app.Adapter.adp_lista_transportista;
import com.example.pre_venta_app.Datos.DTransportista;
import com.example.pre_venta_app.Entidad.Transportista;
import com.example.pre_venta_app.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class an_transportista extends AppCompatActivity {

    public static TextView tvcod_transportita, tv_des_transportista, tvdireccion, tvtelefono, tvcorreo, tvplaca ;
    private Transportista tra;
    Button btnguardar, btncancelar;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_transportista);

        context = this;

        tvcod_transportita = (TextView) findViewById(R.id.txtcod_transportista);
        tv_des_transportista = (TextView) findViewById(R.id.txttransportista);
        tvdireccion = (TextView) findViewById(R.id.txtdireccion_tra);
        tvtelefono = (TextView) findViewById(R.id.txttelefono_tra);
        tvcorreo = (TextView) findViewById(R.id.txtcorreo_tra);
        tvplaca = (TextView) findViewById(R.id.txtplaca);

        btnguardar = (Button) findViewById(R.id.btnguardar);
        btncancelar = (Button) findViewById(R.id.btncanelar_cli);

        if (adp_lista_transportista.flag_modificar) {
            tra = (Transportista) getIntent().getExtras().getSerializable("itemDetail");
            tvcod_transportita.setText(tra.getCod_transportista());
            tv_des_transportista.setText(tra.getDes_transportista());
            tvdireccion.setText(tra.getDireccion());
            tvtelefono.setText(tra.getTelefono());
            tvcorreo.setText(tra.getCorreo());
            tvplaca.setText(tra.getPlaca());
        }

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                adp_lista_transportista.flag_modificar = false;
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transportista t = new Transportista();

                if (tvcod_transportita.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el CODIGO TRANSPORTISTA").show();
                    tvcod_transportita.requestFocus();
                    return;
                }

                if (tv_des_transportista.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese la DESCRIPCION DEL TRANSPORTISTA").show();
                    tv_des_transportista.requestFocus();
                    return;
                }

                t.setCod_transportista(tvcod_transportita.getText().toString().trim());
                t.setDes_transportista(tv_des_transportista.getText().toString().trim());
                t.setDireccion(tvdireccion.getText().toString().trim());
                t.setTelefono(tvtelefono.getText().toString().trim());
                t.setCorreo(tvcorreo.getText().toString().trim());
                t.setPlaca(tvplaca.getText().toString().trim());

                Log.e("flag_modificar", String.valueOf(adp_lista_transportista.flag_modificar));

                if (adp_lista_transportista.flag_modificar) {
                    if (DTransportista.Actualizar_transportista(t).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN ACTUALIZADO LOS DATOS DEL CLIENTE CORRECTAMENTE...").show();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DTransportista.Actualizar_transportista(t)).show();
                    }
                }else{
                    if (DTransportista.Insertar_transportista(t).equals("1")) {
                        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText("SE HAN GUARDADO LOS DATOS DEL CLIENTE CORRECTAMENTE...").show();
                        Limpar();
                    }else{
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(DTransportista.Insertar_transportista(t)).show();
                    }
                }
            }
        });
    }

    void Limpar()
    {
        tvcod_transportita.setText("");
        tv_des_transportista.setText("");
        tvdireccion.setText("");
        tvtelefono.setText("");
        tvcorreo.setText("");
        tvplaca.setText("");
    }

}

package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pre_venta_app.Datos.Dlogin;
import com.example.pre_venta_app.Entidad.Login;
import com.example.pre_venta_app.R;

import java.sql.SQLException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class an_login extends AppCompatActivity {
    Context context;
    Button btn_ingresar, btn_cancelar;
    EditText txtusuario, txtcontraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        btn_ingresar = findViewById(R.id.btningresar);
        btn_cancelar = findViewById(R.id.btncancelar);
        txtusuario = findViewById(R.id.txtusuario);
        txtcontraseña = findViewById(R.id.txtcontraseña);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login l = new Login();

                if (txtusuario.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese el Usuario").show();
                    txtusuario.requestFocus();
                    return;
                }

                if (txtcontraseña.getText().length() == 0){
                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese la Contraseña").show();
                    txtcontraseña.requestFocus();
                    return;
                }

                l.setCod_usu(txtusuario.getText().toString());
                l.setClave(txtcontraseña.getText().toString());

                if (Dlogin.AccesoLogin(l).equals("1")){
                    Intent f = new Intent(an_login.this, an_menu.class);
                    startActivity(f);
                    txtcontraseña.setText("");
                    txtusuario.setText("");
                    finish();
                }else {
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(Dlogin.AccesoLogin(l)).show();
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
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

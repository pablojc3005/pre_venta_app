package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pre_venta_app.R;

import org.w3c.dom.Text;

public class an_presupuesto extends AppCompatActivity {

    Button btncliente, btntransportista, btnarticulo, btnguardar, btnsalir;
    public static TextView tvcod_cliente, tvruc, tvcliente, tvtransportista;
    TextView tvcod_presupuesto, tvigv, tvsub_total, tvtotal;
    EditText etcod_Articulo;
    Spinner spforma_pago;
    ListView lvlista_articulo;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_presupuesto);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        tvcod_presupuesto = (TextView) findViewById(R.id.tvnro_presupuesto);
        tvcod_cliente = (TextView) findViewById(R.id.tvcod_cliente);
        tvruc = (TextView) findViewById(R.id.tvruc);
        tvcliente = (TextView) findViewById(R.id.tvdes_cliente);
        tvtransportista = (TextView) findViewById(R.id.tvtransportista);
        tvigv = (TextView) findViewById(R.id.tvigv);
        tvsub_total = (TextView) findViewById(R.id.tvsub_total);
        tvtotal = (TextView) findViewById(R.id.tvtotal);

        etcod_Articulo = (EditText) findViewById(R.id.etcod_articulo);

        spforma_pago = (Spinner) findViewById(R.id.spforma_pago);

        lvlista_articulo = (ListView) findViewById(R.id.lvlista_articulo);

        btncliente = (Button) findViewById(R.id.btnbuscar_cliente);
        btntransportista = (Button) findViewById(R.id.btnbuscar_transporte);
        btnarticulo = (Button) findViewById(R.id.btnbuscar_articulo);
        btnguardar = (Button) findViewById(R.id.btnguardar);
        btnsalir = (Button) findViewById(R.id.btnsalir);

        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_clientes.class);
                startActivity(f);
            }
        });

        btntransportista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_transportita.class);
                startActivity(f);
            }
        });

        btnarticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

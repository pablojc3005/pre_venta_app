package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pre_venta_app.Datos.DArticulo;
import com.example.pre_venta_app.Datos.DGuia;
import com.example.pre_venta_app.Entidad.Detalle_guia;
import com.example.pre_venta_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class an_presupuesto extends AppCompatActivity {

    Button btncliente, btntransportista, btnarticulo, btnguardar, btnsalir;
    public static TextView tvcod_cliente, tvruc, tvcliente, tvtransportista;
    public static boolean est_seleccion_cliente = false, est_seleccion_transporte = false;
    TextView tvcod_presupuesto, tvigv, tvsub_total, tvtotal;
    public static EditText etcod_Articulo;
    ArrayAdapter<String> myAdapter_forma_pago;
    Detalle_guia d = null;
    ArrayList<Detalle_guia> arreglo_det_guia = new ArrayList<Detalle_guia>();
    ArrayAdapter<Detalle_guia> adaptador_guia;
    Spinner spforma_pago;
    ListView lvlista_articulo;
    String cod_articulo = "", nom_articulo = "";
    Double Importe = 0d;
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
        tvcliente = (TextView) findViewById(R.id.tvnom_cliente);
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

        // cargar FORMA DE PAGO
        myAdapter_forma_pago = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, DGuia.Lista_forma_pago());
        myAdapter_forma_pago.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spforma_pago.setAdapter(myAdapter_forma_pago);

        btncliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                est_seleccion_cliente = true;
                Intent f = new Intent(context, an_lista_clientes.class);
                startActivity(f);
            }
        });

        btntransportista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                est_seleccion_transporte = true;
                Intent f = new Intent(context, an_lista_transportita.class);
                startActivity(f);
            }
        });

        btnarticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(context, an_lista_productos.class);
                startActivity(f);
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

        etcod_Articulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("S : ", s.toString());
                if (s.length() >= 8){
                    Agregar();
                }
            }
        });

    }

    public void Agregar() {
        cod_articulo = etcod_Articulo.getText().toString().substring(0, etcod_Articulo.getText().toString().indexOf("="));
        nom_articulo = etcod_Articulo.getText().toString().substring((etcod_Articulo.getText().toString().indexOf("=")+1), etcod_Articulo.getText().toString().length());

        boolean repetido = false;
        Importe = 0d;

        if(cod_articulo.length() == 0)
        {
            Toast.makeText(context, "INGRESE UN CODIDO DE ARTICULO VALIDO", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.e("tamano lista ", String.valueOf(arreglo_det_guia.size()));
        if(arreglo_det_guia.size() > 0) {
            for (int i = 0; i < arreglo_det_guia.size(); i++) {
                if (arreglo_det_guia.get(i).getCod_articulo().trim().equals(cod_articulo)) {
                    repetido = true;
                } else {
                    if(DArticulo.Existe_articulo(cod_articulo) == 0){
                        Toast.makeText(context, "CODIGO DE ARTICULO "+cod_articulo+" NO EXISTE EN LA LISTA", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }else{
            if(DArticulo.Existe_articulo(cod_articulo) == 0){
                Toast.makeText(context, "CODIGO DE ARTICULO "+cod_articulo+" NO EXISTE EN LA LISTA", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (repetido == true) {
            Toast.makeText(context, "CODIGO DE ARTICULO "+cod_articulo+" YA EXISTE EN LA LISTA", Toast.LENGTH_SHORT).show();
        } else {
            int item = 0;
            if (arreglo_det_guia.size() > 0) {
                item = arreglo_det_guia.size();
            }
            //precio = String.valueOf(DArticulo.Precio_articulo(cod_articulo));
            d = new Detalle_guia();
            d.setCod_registro("1");
            d.setSecuencia(String.valueOf(item));
            d.setCod_articulo(cod_articulo);
            d.setArticulo(nom_articulo);
            d.setCantidad("1.00");
            d.setPrecio("1.00");
            arreglo_det_guia.add(d);
        }

        // para el importe
        etcod_Articulo.setText("");
        for (int f = 0; f < arreglo_det_guia.size(); f++) {
            arreglo_det_guia.get(f).getCantidad();
            Importe += (Double.parseDouble(arreglo_det_guia.get(f).getPrecio()) * Double.parseDouble(arreglo_det_guia.get(f).getCantidad()));
        }
        adaptador_guia = new myListAdapter_detalle_guia();
        lvlista_articulo.setAdapter(adaptador_guia);

        tvtotal.setText( String.valueOf(Importe));
    }

    private class myListAdapter_detalle_guia extends ArrayAdapter<Detalle_guia> {
        public myListAdapter_detalle_guia(){

            super(an_presupuesto.this, R.layout.item_view_detalle_guia, arreglo_det_guia);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View itemView=convertView;
            try
            {
                if(itemView == null)
                {
                    itemView=getLayoutInflater().inflate(R.layout.item_view_detalle_guia, parent, false);
                }
                Detalle_guia current = arreglo_det_guia.get(position);

                /*TextView txt_id = (TextView) itemView.findViewById(R.id.tvid);
                txt_id.setText(String.valueOf(current.getId()));*/

                /*TextView txt_item = (TextView) itemView.findViewById(R.id.tvitem);
                txt_item.setText(String.valueOf(current.getItem()));*/

                TextView txt_desc_art = (TextView) itemView.findViewById(R.id.tvdesc_art);
                txt_desc_art.setText(String.valueOf(current.getArticulo()));

                TextView txt_precio = (TextView) itemView.findViewById(R.id.tvprecio);
                txt_precio.setText(String.valueOf(current.getPrecio()));

                TextView txt_cantidad = (TextView) itemView.findViewById(R.id.tvcantidad);
                txt_cantidad.setText(String.valueOf(current.getCantidad()));

            }
            catch (Exception ex)
            {
                Toast.makeText(an_presupuesto.this, "myListAdapter_Detalle_venta / " + ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            return itemView;
        }
    }
}

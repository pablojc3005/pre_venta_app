package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pre_venta_app.Datos.DArticulo;
import com.example.pre_venta_app.Datos.DGuia;
import com.example.pre_venta_app.Datos.DTransportista;
import com.example.pre_venta_app.Entidad.Detalle_guia;
import com.example.pre_venta_app.Entidad.Guia;
import com.example.pre_venta_app.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.pre_venta_app.Presentacion.an_lista_productos.*;

public class an_presupuesto extends AppCompatActivity {

    Button btncliente, btntransportista, btnarticulo, btnguardar, btnsalir;
    public static TextView tvcod_cliente, tvruc, tvcliente, tvtransportista;
    public static boolean est_seleccion_cliente = false, est_seleccion_transporte = false;
    TextView tvigv, tvsub_total, tvtotal;
    public static EditText etcod_Articulo;
    ArrayAdapter<String> myAdapter_forma_pago;
    Detalle_guia d = null;
    ArrayList<Detalle_guia> arreglo_det_guia = new ArrayList<Detalle_guia>();
    ArrayAdapter<Detalle_guia> adaptador_guia;
    Guia g = null;
    Spinner spforma_pago;
    ListView lvlista_articulo;
    String mensaje = "";
    String cod_articulo = "", nom_articulo = "";
    Double Subtotal = 0d, Igv = 0d, Total = 0d, precio = 0d;;
    Context context;
    boolean est_guardado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_presupuesto);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;


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

        lvlista_articulo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showInputBox(arreglo_det_guia.get(position), position);
                //dialog.dismiss();
            }
        });

        btnarticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f2 = new Intent(context, an_lista_productos.class);
                startActivity(f2);
            }
        });

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                dialog.setTitleText("¿ ESTA SEGURO QUE DESEA GENERAR EL PRESUPUESTO ?");
                dialog.setConfirmText("SI");
                dialog.setCancelable(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        if(tvcod_cliente.getText().toString().length() == 0)
                        {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Seleccione el Cliente").show();
                            sDialog.dismissWithAnimation();
                            return;
                        }

                        if(tvtransportista.getText().toString().length() == 0)
                        {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Seleccione el Transportista").show();
                            sDialog.dismissWithAnimation();
                            return;
                        }

                        if(spforma_pago.getSelectedItem().toString().length() == 0)
                        {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Seleccione el Vendedor").show();
                            sDialog.dismissWithAnimation();
                            return;
                        }

                        if (lvlista_articulo.getCount() == 0)
                        {
                            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Ingrese los Articulos para la Venta").show();
                            sDialog.dismissWithAnimation();
                            return;
                        }
                        new Generar_Presupuesto(context).execute();
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
                if (s.length() >= 6){
                    Agregar();
                }
            }
        });
    }

    public void Agregar() {

        cod_articulo = etcod_Articulo.getText().toString().substring(0, etcod_Articulo.getText().toString().indexOf("="));
        nom_articulo = etcod_Articulo.getText().toString().substring((etcod_Articulo.getText().toString().indexOf("=")+1), etcod_Articulo.getText().toString().length());

        boolean repetido = false;
        Subtotal = 0d;
        Igv = 0d;
        Total = 0d;

        if(cod_articulo.length() == 0)
        {
            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("INGRESE UN CODIDO DE ARTICULO VALIDO").show();
            return;
        }

        Log.e("tamaño lista ", String.valueOf(arreglo_det_guia.size()));
        if(arreglo_det_guia.size() > 0) {
            for (int i = 0; i < arreglo_det_guia.size(); i++) {
                if (arreglo_det_guia.get(i).getCod_articulo().trim().equals(cod_articulo)) {
                    repetido = true;
                } else {
                    if(DArticulo.Existe_articulo(cod_articulo) == 0){
                        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("CODIGO DE ARTICULO "+cod_articulo+" NO EXISTE EN LA LISTA").show();
                        return;
                    }
                }
            }
        } else {
            if(DArticulo.Existe_articulo(cod_articulo) == 0){
                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("CODIGO DE ARTICULO "+cod_articulo+" NO EXISTE EN LA LISTA").show();
                return;
            }
        }
        if (repetido == true) {
            new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("CODIGO DE ARTICULO "+cod_articulo+" YA EXISTE EN LA LISTA").show();
        } else {
            int item = 0;
            if (arreglo_det_guia.size() > 0) {
                item = arreglo_det_guia.size();
            }

            precio = DArticulo.Precio_articulo(cod_articulo);

            d = new Detalle_guia();
            //d.setCod_registro("1");
            d.setSecuencia(String.valueOf(item));
            d.setCod_articulo(cod_articulo);
            d.setArticulo(nom_articulo);
            d.setCantidad("1.00");
            d.setPrecio(String.format("%.2f",precio));
            arreglo_det_guia.add(d);
        }

        // para el importe
        etcod_Articulo.setText("");

        for (int f = 0; f < arreglo_det_guia.size(); f++) {
            Total += (Double.parseDouble(arreglo_det_guia.get(f).getPrecio()) * Double.parseDouble(arreglo_det_guia.get(f).getCantidad()));
        }
        Igv = Total * 0.18;
        Subtotal = Total - Igv;

        adaptador_guia = new myListAdapter_detalle_guia();
        lvlista_articulo.setAdapter(adaptador_guia);
        tvsub_total.setText( String.format("%.2f", Subtotal));
        tvigv.setText(String.format("%.2f", Igv));
        tvtotal.setText(String.format("%.2f",Total));
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

    private class Generar_Presupuesto extends AsyncTask<Integer, Void, Integer> {
        Context context;
        SweetAlertDialog pdialog;
        final TextView tvcod_presupuesto = (TextView) findViewById(R.id.tvnro_presupuesto);

        public Generar_Presupuesto(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            pdialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            pdialog.setTitle("CARGANDO...");
            pdialog.setContentText("ESPERE POR FAVOR, SE ESTAN GUARDANDO LOS DATOS...");
            pdialog.setCancelable(false);
            pdialog.show();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {

                g = new Guia();
                g.setCodcliente(tvcod_cliente.getText().toString().trim());
                g.setCodtransportis(DTransportista.Codigo_transportista(tvtransportista.getText().toString().trim()));
                g.setCodFormaPago(spforma_pago.getSelectedItem().toString().substring(0,2));
                g.setSub_total(Double.parseDouble(tvsub_total.getText().toString()));
                g.setIgv(Double.parseDouble(tvigv.getText().toString()));
                g.setTotal(Double.parseDouble(tvtotal.getText().toString()));

                List<Detalle_guia> lista = new ArrayList<Detalle_guia>();

                Log.e("lista det", String.valueOf(arreglo_det_guia.size()));

                for (int i = 0; i < arreglo_det_guia.size(); i++)
                {
                    d = new Detalle_guia();
                    d.setSecuencia(arreglo_det_guia.get(i).getSecuencia());
                    d.setCod_articulo(arreglo_det_guia.get(i).getCod_articulo());
                    //d.setArticulo(arreglo_det_guia.get(i).getArticulo());
                    d.setCantidad(arreglo_det_guia.get(i).getCantidad());
                    d.setPrecio(arreglo_det_guia.get(i).getPrecio());
                    lista.add(d);
                }
                g.setLista_detalle(lista);

                if (DGuia.Guardar_guia(g)) {
                    est_guardado = true;
                    mensaje = "PRESUPUESTO GENERADO CORRECTAMENTE...";
                }else{
                    est_guardado = false;
                    mensaje = "ALGO SALIDO MAL...";
                }

            } catch (Exception e) {
                Log.e("ERROR DE ASINCRONIA", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer i) {
            pdialog.dismiss();
            if (est_guardado){
                new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText(mensaje).show();
                tvcod_presupuesto.setText(String.valueOf(DGuia.Ultimo_registro()));
                Limpiar();
            }else  {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText(mensaje).show();
            }
        }
    }

    void Limpiar(){
        tvcod_cliente.setText("");
        tvruc.setText("");
        tvcliente.setText("");
        tvtransportista.setText("");
        arreglo_det_guia.clear();
        adaptador_guia.notifyDataSetChanged();
        tvsub_total.setText("0.00");
        tvigv.setText("0.00");
        tvtotal.setText("0.00");
    }

    public void showInputBox(final Detalle_guia olditem, final int index)
    {
        final Dialog dlg = new Dialog(context);
        //dlg.setTitle("INGRESAR LA CANTIDAD");
        dlg.setContentView(R.layout.item_input_cantidad);
        final EditText ed_cantidad = (EditText)dlg.findViewById(R.id.et_cantidad);
        final EditText ed_precio   = (EditText)dlg.findViewById(R.id.et_precio);

        Button btn_aceptar = (Button)dlg.findViewById(R.id.btnaceptar);
        ed_cantidad.setText(String.valueOf(olditem.getCantidad()));
        ed_precio.setText(String.valueOf(olditem.getPrecio()));

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Detalle_guia ta = arreglo_det_guia.get(index);
                ta.setCantidad(ed_cantidad.getText().toString());
                ta.setPrecio(ed_precio.getText().toString());
                arreglo_det_guia.set(index, ta);
                adaptador_guia.notifyDataSetChanged();
                Total = 0d;
                Igv = 0d;
                Total = 0d;

                for (int i = 0; i < arreglo_det_guia.size(); i++)
                {
                    Total += Double.parseDouble(arreglo_det_guia.get(i).getPrecio()) * Double.parseDouble( arreglo_det_guia.get(i).getCantidad());
                }
                Igv = Total * 0.18;
                Subtotal = Total - Igv;

                tvigv.setText(String.format("%.2f",Igv));
                tvsub_total.setText(String.format("%.2f",Subtotal));
                tvtotal.setText(String.format("%.2f",Total));
                dlg.dismiss();
            }
        });
        dlg.show();
    }
}

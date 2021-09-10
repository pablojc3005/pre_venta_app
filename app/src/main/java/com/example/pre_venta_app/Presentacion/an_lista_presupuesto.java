package com.example.pre_venta_app.Presentacion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pre_venta_app.Adapter.adp_lista_presupuesto;
import com.example.pre_venta_app.Datos.DGuia;
import com.example.pre_venta_app.Entidad.Guia;
import com.example.pre_venta_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class an_lista_presupuesto extends AppCompatActivity implements adp_lista_presupuesto.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    public static adp_lista_presupuesto adapter;
    private List<Guia> items;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_lista_presupuesto);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        initViews();
        initValues();
        initListener();

        FloatingActionButton fab = findViewById(R.id.fabpresupuesto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(context, an_presupuesto.class);
                startActivity(f);
            }
        });
    }

    private void initViews() {
        rvLista = findViewById(R.id.lv_lista_presupuesto);
        svSearch = findViewById(R.id.search_presupuesto);
        svSearch.requestFocus();
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new adp_lista_presupuesto(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Guia> getItems() {
        ArrayList<Guia> Lista = DGuia.Lista_presupuesto();
        List<Guia> itemLists = new ArrayList<>();

        for (int i = 0; i < Lista.size(); i++) {
            itemLists.add(new Guia(Lista.get(i).getCod_registro(), Lista.get(i).getFecha_registro(),
                                   Lista.get(i).getCodcliente(), Lista.get(i).getRuc(),Lista.get(i).getDescliente(),
                                   Lista.get(i).getCodtransportis(),Lista.get(i).getDestransportis(),Lista.get(i).getCodFormaPago(),
                                   Lista.get(i).getCod_moneda(), Lista.get(i).getTotal()));
        }
        return itemLists;
    }

    @Override
    public void itemClick(Guia item) {
       /*if (an_presupuesto.es){
            an_presupuesto.tvcod_cliente.setText(item.getCodcliente());
            an_presupuesto.tvruc.setText(item.getRuc());
            an_presupuesto.tvcliente.setText(item.getDescliente());
        }*/
        //an_presupuesto.est_seleccion_cliente = false;
        finish();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}

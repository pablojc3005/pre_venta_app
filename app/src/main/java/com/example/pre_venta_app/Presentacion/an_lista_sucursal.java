package com.example.pre_venta_app.Presentacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.example.pre_venta_app.Adapter.adp_lista_sucursal;
import com.example.pre_venta_app.Datos.DSucursal;
import com.example.pre_venta_app.Entidad.Sucursal;
import com.example.pre_venta_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class an_lista_sucursal extends AppCompatActivity implements adp_lista_sucursal.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    public static adp_lista_sucursal adapter;
    private List<Sucursal> items;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_lista_sucursal);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        initViews();
        initValues();
        initListener();

        FloatingActionButton fab = findViewById(R.id.fabsucursal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(context, an_sucursal.class);
                startActivity(f);
            }
        });
    }

    private void initViews() {
        rvLista = findViewById(R.id.lv_lista_sucursal);
        svSearch = findViewById(R.id.search_sucursal);
        svSearch.requestFocus();
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new adp_lista_sucursal(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Sucursal> getItems() {
        ArrayList<Sucursal> Lista = DSucursal.Lista_sucursales();
        List<Sucursal> itemLists = new ArrayList<>();

        for (int i = 0; i < Lista.size(); i++) {
            itemLists.add(new Sucursal(Lista.get(i).getCod_cliente(), Lista.get(i).getDesc_cliente(), Lista.get(i).getCod_sucursal(), Lista.get(i).getDes_sucursal(),Lista.get(i).getDireccion()));
        }
        return itemLists;
    }

    @Override
    public void itemClick(Sucursal item) {
        /*if (an_presupuesto.est_seleccion_cliente){
            an_presupuesto.tvcod_cliente.setText(item.getCod_cliente());
            an_presupuesto.tvruc.setText(item.getRuc());
            an_presupuesto.tvcliente.setText(item.getDes_cliente());
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

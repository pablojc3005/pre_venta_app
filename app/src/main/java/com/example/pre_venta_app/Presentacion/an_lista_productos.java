package com.example.pre_venta_app.Presentacion;

import android.content.Context;
import android.os.Bundle;

import com.example.pre_venta_app.Adapter.adp_lista_articulos;
import com.example.pre_venta_app.Datos.DArticulo;
import com.example.pre_venta_app.Entidad.Articulo;
import com.example.pre_venta_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class an_lista_productos extends AppCompatActivity implements adp_lista_articulos.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    public static adp_lista_articulos adapter;
    private List<Articulo> items;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_lista_productos);
        context = this;

        initViews();
        initValues();
        initListener();
    }

    private void initViews() {
        rvLista = findViewById(R.id.lv_lista_articulo);
        svSearch = findViewById(R.id.search_articulo);
        svSearch.requestFocus();
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new adp_lista_articulos(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Articulo> getItems() {
        ArrayList<Articulo> Lista = DArticulo.Lista_articulos();
        List<Articulo> itemLists = new ArrayList<>();

        for (int i = 0; i < Lista.size(); i++) {
            itemLists.add(new Articulo(Lista.get(i).getCod_articulo(), Lista.get(i).getDesc_articulo(), Lista.get(i).getCod_und(), Lista.get(i).getUnd_medida(),Lista.get(i).getSaldo(),Lista.get(i).getPrecio()));
        }
        return itemLists;
    }

    @Override
    public void itemClick(Articulo item) {
        an_presupuesto.etcod_Articulo.setText(item.getCod_articulo()+"="+item.getDesc_articulo());
        an_presupuesto.precio = item.getPrecio();
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

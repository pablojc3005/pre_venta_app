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

import com.example.pre_venta_app.Adapter.adp_lista_transportista;
import com.example.pre_venta_app.Datos.DTransportista;
import com.example.pre_venta_app.Entidad.Transportista;
import com.example.pre_venta_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class an_lista_transportita extends AppCompatActivity implements adp_lista_transportista.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    private adp_lista_transportista adapter;
    private List<Transportista> items;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_lista_transportista);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        initViews();
        initValues();
        initListener();

        FloatingActionButton fab = findViewById(R.id.fabtransportista);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(context, an_transportista.class);
                startActivity(f);
            }
        });

    }

    private void initViews() {
        rvLista = findViewById(R.id.lv_lista_transportista);
        svSearch = findViewById(R.id.search_transportista);
        svSearch.requestFocus();
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new adp_lista_transportista(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Transportista> getItems() {
        ArrayList<Transportista> Lista = DTransportista.Lista_transportista();
        List<Transportista> itemLists = new ArrayList<>();

        for (int i = 0; i < Lista.size(); i++) {
            itemLists.add(new Transportista(Lista.get(i).getCod_transportista(), Lista.get(i).getDes_transportista(), Lista.get(i).getDireccion(),Lista.get(i).getTelefono(),Lista.get(i).getCorreo(),Lista.get(i).getPlaca()));
        }

        return itemLists;
    }

    @Override
    public void itemClick(Transportista item) {
        an_presupuesto.tvtransportista.setText(item.getDes_transportista());
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

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

import com.example.pre_venta_app.Adapter.adp_lista_clientes;
import com.example.pre_venta_app.Datos.DCliente;
import com.example.pre_venta_app.Entidad.Cliente;
import com.example.pre_venta_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class an_lista_clientes extends AppCompatActivity implements adp_lista_clientes.RecyclerItemClick, SearchView.OnQueryTextListener {

    private RecyclerView rvLista;
    private SearchView svSearch;
    public static adp_lista_clientes adapter;
    private List<Cliente> items;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ln_lista_clientes);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        context = this;

        initViews();
        initValues();
        initListener();

        FloatingActionButton fab = findViewById(R.id.fabcliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f = new Intent(context, an_cliente.class);
                startActivity(f);
            }
        });

    }

    private void initViews() {
        rvLista = findViewById(R.id.lv_lista_cliente);
        svSearch = findViewById(R.id.search_cliente);
        svSearch.requestFocus();
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new adp_lista_clientes(items, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Cliente> getItems() {
        ArrayList<Cliente> Lista = DCliente.Lista_clientes();
        List<Cliente> itemLists = new ArrayList<>();

        for (int i = 0; i < Lista.size(); i++) {
            itemLists.add(new Cliente(Lista.get(i).getCod_cliente(), Lista.get(i).getRuc(), Lista.get(i).getDes_cliente(), Lista.get(i).getDireccion(),Lista.get(i).getTelefono(),Lista.get(i).getCorreo(),Lista.get(i).getContacto()));
        }
        return itemLists;
    }

    @Override
    public void itemClick(Cliente item) {
        an_presupuesto.tvcod_cliente.setText(item.getCod_cliente());
        an_presupuesto.tvruc.setText(item.getRuc());
        an_presupuesto.tvcliente.setText(item.getDes_cliente());
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

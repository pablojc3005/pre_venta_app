package com.example.pre_venta_app.Adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pre_venta_app.Entidad.Cliente;
import com.example.pre_venta_app.Presentacion.an_cliente;
import com.example.pre_venta_app.Presentacion.an_presupuesto;
import com.example.pre_venta_app.Presentacion.an_sucursal;
import com.example.pre_venta_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class adp_lista_clientes extends RecyclerView.Adapter<adp_lista_clientes.RecyclerHolder> {
    public static boolean flag_modificar = false;
    private List<Cliente> items;
    private List<Cliente> originalItems;
    private adp_lista_clientes.RecyclerItemClick itemClick;

    public adp_lista_clientes(List<Cliente> items, adp_lista_clientes.RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public adp_lista_clientes.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_cliente, parent, false);
        return new adp_lista_clientes.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adp_lista_clientes.RecyclerHolder holder, final int position) {
        final Cliente item = items.get(position);
        holder.tvid.setText(item.getCod_cliente());
        holder.tvruc.setText(item.getRuc());
        holder.tvnom_cliente.setText(item.getDes_cliente());
        holder.tvdireccion.setText(item.getDireccion());
        holder.tvtelefono.setText(item.getTelefono());
        holder.tvcorreo.setText(item.getCorreo());
        holder.tvcontacto.setText(item.getContacto());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (an_presupuesto.est_seleccion_cliente || an_sucursal.est_seleccion_cliente2){
                    itemClick.itemClick(item);
                }else{
                    Intent intent = new Intent(holder.itemView.getContext(), an_cliente.class);
                    intent.putExtra("itemDetail", item);
                    holder.itemView.getContext().startActivity(intent);
                    flag_modificar = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<Cliente> collect = originalItems.stream()
                        .filter(i -> i.getDes_cliente().toUpperCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (Cliente i : originalItems) {
                    if (i.getDes_cliente().toUpperCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvid;
        private TextView tvruc;
        private TextView tvnom_cliente;
        private TextView tvdireccion;
        private TextView tvtelefono;
        private TextView tvcorreo;
        private TextView tvcontacto;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvid = itemView.findViewById(R.id.tvcod_cliente);
            tvruc = itemView.findViewById(R.id.tvruc);
            tvnom_cliente = itemView.findViewById(R.id.tvdes_cliente);
            tvdireccion = itemView.findViewById(R.id.tvdireccion);
            tvtelefono = itemView.findViewById(R.id.tvtelefono);
            tvcorreo = itemView.findViewById(R.id.tvcorreo);
            tvcontacto = itemView.findViewById(R.id.tvcontacto);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Cliente item);
    }
}

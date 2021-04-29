package com.example.pre_venta_app.Adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pre_venta_app.Entidad.Articulo;
import com.example.pre_venta_app.Presentacion.an_cliente;
import com.example.pre_venta_app.Presentacion.an_presupuesto;
import com.example.pre_venta_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class adp_lista_articulos extends RecyclerView.Adapter<adp_lista_articulos.RecyclerHolder> {
    public static boolean flag_modificar = false;
    private List<Articulo> items;
    private List<Articulo> originalItems;
    private adp_lista_articulos.RecyclerItemClick itemClick;

    public adp_lista_articulos(List<Articulo> items, adp_lista_articulos.RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public adp_lista_articulos.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_articulo, parent, false);
        return new adp_lista_articulos.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adp_lista_articulos.RecyclerHolder holder, final int position) {
        final Articulo item = items.get(position);
        holder.tvcod_articulo.setText(item.getCod_articulo());
        holder.tvdes_articulo.setText(item.getDesc_articulo());
        holder.tvsaldo.setText(String.valueOf(item.getSaldo()));
        holder.tvprecio.setText(String.valueOf(item.getPrecio()));
        holder.tvcod_und.setText(item.getCod_und());
        holder.tvdes_und.setText(item.getUnd_medida());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
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
                List<Articulo> collect = originalItems.stream()
                        .filter(i -> i.getDesc_articulo().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (Articulo i : originalItems) {
                    if (i.getDesc_articulo().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvcod_articulo;
        private TextView tvdes_articulo;
        private TextView tvsaldo;
        private TextView tvprecio;
        private TextView tvcod_und;
        private TextView tvdes_und;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvcod_articulo = itemView.findViewById(R.id.tvcod_articulo);
            tvdes_articulo = itemView.findViewById(R.id.tvdes_articulo);
            tvsaldo = itemView.findViewById(R.id.tvsaldo);
            tvprecio = itemView.findViewById(R.id.tvprecio);
            tvcod_und = itemView.findViewById(R.id.tvcod_und);
            tvdes_und = itemView.findViewById(R.id.tvdes_unidad);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Articulo item);
    }
}

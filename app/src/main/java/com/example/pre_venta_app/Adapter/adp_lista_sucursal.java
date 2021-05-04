package com.example.pre_venta_app.Adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pre_venta_app.Entidad.Sucursal;
import com.example.pre_venta_app.Presentacion.an_sucursal;
import com.example.pre_venta_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class adp_lista_sucursal extends RecyclerView.Adapter<adp_lista_sucursal.RecyclerHolder> {
    public static boolean flag_modificar = false;
    private List<Sucursal> items;
    private List<Sucursal> originalItems;
    private adp_lista_sucursal.RecyclerItemClick itemClick;

    public adp_lista_sucursal(List<Sucursal> items, adp_lista_sucursal.RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public adp_lista_sucursal.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_sucursal, parent, false);
        return new adp_lista_sucursal.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adp_lista_sucursal.RecyclerHolder holder, final int position) {
        final Sucursal item = items.get(position);
        holder.tvcod_cliente.setText(item.getCod_cliente());
        holder.tvnom_cliente.setText(item.getDesc_cliente());
        holder.tvcod_sucursal.setText(item.getCod_sucursal());
        holder.tvnom_sucursal.setText(item.getDes_sucursal());
        holder.tvdireccion.setText(item.getDireccion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), an_sucursal.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
                flag_modificar = true;
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
                List<Sucursal> collect = originalItems.stream()
                        .filter(i -> i.getDes_sucursal().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (Sucursal i : originalItems) {
                    if (i.getDes_sucursal().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvcod_cliente;
        private TextView tvnom_cliente;
        private TextView tvcod_sucursal;
        private TextView tvnom_sucursal;
        private TextView tvdireccion;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvcod_cliente = itemView.findViewById(R.id.tvcod_cliente);
            tvnom_cliente = itemView.findViewById(R.id.tvcliente);
            tvcod_sucursal = itemView.findViewById(R.id.tvcod_sucursal);
            tvnom_sucursal = itemView.findViewById(R.id.tvsucursal);
            tvdireccion = itemView.findViewById(R.id.tvdireccion);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Sucursal item);
    }
}

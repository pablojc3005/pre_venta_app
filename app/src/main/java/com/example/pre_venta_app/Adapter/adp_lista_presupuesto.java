package com.example.pre_venta_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pre_venta_app.Entidad.Guia;
import com.example.pre_venta_app.Presentacion.an_presupuesto;
import com.example.pre_venta_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class adp_lista_presupuesto extends RecyclerView.Adapter<adp_lista_presupuesto.RecyclerHolder> {
    public static boolean flag_modificar = false;
    private List<Guia> items;
    private List<Guia> originalItems;
    ArrayAdapter<Guia> myAdapter_fp;
    Context context;
    private adp_lista_presupuesto.RecyclerItemClick itemClick;

    public adp_lista_presupuesto(List<Guia> items, adp_lista_presupuesto.RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public adp_lista_presupuesto.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_presupuesto, parent, false);
        return new adp_lista_presupuesto.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adp_lista_presupuesto.RecyclerHolder holder, final int position) {
        final Guia item = items.get(position);
        holder.tvcod_presupuesto.setText(String.valueOf(item.getCod_registro()));
        holder.tvfecha_presupuesto.setText(item.getFecha_registro());
        holder.tvcod_cliente.setText(item.getCodcliente());
        holder.tvnom_cliente.setText(item.getDescliente());
        holder.tvmoneda.setText(item.getCod_moneda());
        holder.tvmonto.setText(String.valueOf(item.getTotal()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), an_presupuesto.class);
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
                List<Guia> collect = originalItems.stream()
                        .filter(i -> i.getDescliente().toUpperCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (Guia i : originalItems) {
                    if (i.getDescliente().toUpperCase().contains(strSearch)) {
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
        private TextView tvcod_presupuesto;
        private TextView tvfecha_presupuesto;
        private TextView tvmoneda;
        private TextView tvmonto;
        Spinner spforma_pago;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvcod_cliente = itemView.findViewById(R.id.tvcod_cliente);
            tvnom_cliente = itemView.findViewById(R.id.tvcliente);
            tvcod_presupuesto = itemView.findViewById(R.id.tvcod_presu);
            tvfecha_presupuesto = itemView.findViewById(R.id.tvfecha_presu);
            spforma_pago  = itemView.findViewById(R.id.spforma_pago);
            tvmoneda = itemView.findViewById(R.id.tvcod_moneda);
            tvmonto = itemView.findViewById(R.id.tvmonto);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Guia item);
    }
}

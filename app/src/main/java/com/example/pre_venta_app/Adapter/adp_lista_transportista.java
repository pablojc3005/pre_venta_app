package com.example.pre_venta_app.Adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pre_venta_app.Entidad.Transportista;
import com.example.pre_venta_app.Presentacion.an_cliente;
import com.example.pre_venta_app.Presentacion.an_presupuesto;
import com.example.pre_venta_app.Presentacion.an_transportista;
import com.example.pre_venta_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class adp_lista_transportista extends RecyclerView.Adapter<adp_lista_transportista.RecyclerHolder> {
    public static boolean flag_modificar = false;
    private List<Transportista> items;
    private List<Transportista> originalItems;
    private adp_lista_transportista.RecyclerItemClick itemClick;

    public adp_lista_transportista(List<Transportista> items, adp_lista_transportista.RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public adp_lista_transportista.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_transportista, parent, false);
        return new adp_lista_transportista.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final adp_lista_transportista.RecyclerHolder holder, final int position) {
        final Transportista item = items.get(position);
        holder.tvid.setText(item.getCod_transportista());
        holder.tvdes_transportista.setText(item.getDes_transportista());
        holder.tvdireccion_tra.setText(item.getDireccion());
        holder.tvtelefono_tra.setText(item.getTelefono());
        holder.tvcorreo_tra.setText(item.getCorreo());
        holder.tvplaca.setText(item.getPlaca());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (an_presupuesto.est_seleccion_transporte){
                    itemClick.itemClick(item);
                }else{
                    Intent intent = new Intent(holder.itemView.getContext(), an_transportista.class);
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
                List<Transportista> collect = originalItems.stream()
                        .filter(i -> i.getDes_transportista().toUpperCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.addAll(collect);
            }
            else {
                items.clear();
                for (Transportista i : originalItems) {
                    if (i.getDes_transportista().toUpperCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView tvid;
        private TextView tvdes_transportista;
        private TextView tvdireccion_tra;
        private TextView tvtelefono_tra;
        private TextView tvcorreo_tra;
        private TextView tvplaca;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            tvid = itemView.findViewById(R.id.tvcod_transportista);
            tvdes_transportista = itemView.findViewById(R.id.tvdes_transportista);
            tvdireccion_tra = itemView.findViewById(R.id.tvdireccion_tra);
            tvtelefono_tra = itemView.findViewById(R.id.tvtelefono_tra);
            tvcorreo_tra = itemView.findViewById(R.id.tvcorreo_tra);
            tvplaca = itemView.findViewById(R.id.tvplaca);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Transportista item);
    }
}


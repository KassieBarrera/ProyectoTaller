package com.example.ProyectoTaller.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ProyectoTaller.Detalle;
import com.example.ProyectoTaller.Model.OrderItem;
import com.example.ProyectoTaller.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<OrderItem> lista;


    public ItemAdapter(ArrayList<OrderItem> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

  /*  public Adaptador(Context context, ArrayList<Peliculas> lista) {
        this.lista = lista;
        this.context = context;
        exampleListFull = new ArrayList<>(lista);
    }*/


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.order_detail, vg, false);  //Vista v = InflarLayout.de(viewgruo.obtenerContexto).inflar(R.layout.carta, viewgroup, falso)
        ViewHolder holder = new ViewHolder(v); // se crea un nuevo  ViewHolder que contenga v
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.nombre.setText(lista.get(i).getNombre());     //holder.titulo.enviartexto(obtienelista.enpunto(i).obtienetitulo)
        holder.estado.setText(lista.get(i).getEstado());
        holder.equipo.setText(lista.get(i).getEquipo());
        //  holder.numeroOrden.setText(lista.get(i).getNumeroOrden());
        holder.observaciones.setText(lista.get(i).getObservaciones());
        holder.tecnico.setText(lista.get(i).getTecnico());

        /*holder.titulo = lista.get(i).getTitulo();
        holder.des = lista.get(i).getDescripcion();
        holder.url = lista.get(i).getUrl();
        holder.date = lista.get(i).getFuncion();
        Glide.with(context).load(lista.get(i).getUrl())
                .error(R.drawable.cineplus)
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;
        private TextView equipo;
        private TextView estado;
        private TextView numeroOrden;
        private TextView observaciones;
        private TextView tecnico;
        private String nOrden;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.cliente);
            equipo = itemView.findViewById(R.id.equipo);
            estado = itemView.findViewById(R.id.estado);
            // numeroOrden = itemView.findViewById(R.id.or);
            observaciones = itemView.findViewById(R.id.observacion);
            tecnico = itemView.findViewById(R.id.tecnico);
        }
    }
}
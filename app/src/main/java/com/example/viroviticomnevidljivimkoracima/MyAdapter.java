package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Ustanova> list;

    public MyAdapter(Context context, ArrayList<Ustanova> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ustanova ustanova = list.get(position);
        holder.naziv.setText(ustanova.getNaziv());
        holder.adresa.setText(ustanova.getAdresa());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView naziv, adresa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.imeObjekta);
            adresa = itemView.findViewById(R.id.adresaObjekta);
        }
    }
}

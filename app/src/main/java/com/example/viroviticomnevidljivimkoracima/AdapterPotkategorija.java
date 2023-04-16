package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPotkategorija extends RecyclerView.Adapter<AdapterPotkategorija.MyViewHolderPotkategorija> {

    Context context;
    ArrayList<Potkategorija> potkategorijaArrayList;

    public AdapterPotkategorija(Context context, ArrayList<Potkategorija> potkategorijaArrayList) {
        this.context = context;
        this.potkategorijaArrayList = potkategorijaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderPotkategorija onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_potkategorije, parent, false);
        return new MyViewHolderPotkategorija(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPotkategorija holder, int position) {
        Potkategorija potkategorija = potkategorijaArrayList.get(position);
        holder.nazivPotkategorije.setText(potkategorija.getNazivPotkategorije());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context.getApplicationContext(), ObjektiPotkategorijeActivity.class);
                intent.putExtra("idPotkategorije", potkategorija.idPotkategorije);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return potkategorijaArrayList.size();
    }

    public static class MyViewHolderPotkategorija extends RecyclerView.ViewHolder {
        TextView nazivPotkategorije;

        public MyViewHolderPotkategorija(@NonNull View itemView) {
            super(itemView);

            nazivPotkategorije = itemView.findViewById(R.id.nazivPotkategorije);

        }
    }
}

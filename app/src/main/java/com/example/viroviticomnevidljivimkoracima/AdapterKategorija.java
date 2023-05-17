package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterKategorija extends RecyclerView.Adapter<AdapterKategorija.MyViewHolderKategorija> {

    Context context;
    ArrayList<Kategorija> kategorijaArrayList;

    public AdapterKategorija(Context context, ArrayList<Kategorija> kategorijaArrayList) {
        this.context = context;
        this.kategorijaArrayList = kategorijaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderKategorija onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new MyViewHolderKategorija(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderKategorija holder, int position) {

        Kategorija kategorija = kategorijaArrayList.get(position);
        Glide.with(context).load(kategorija.slikaKategorije).into(holder.slikaKategorije);
        holder.nazivKategorije.setText(kategorija.getNazivKategorije());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if( !kategorija.idKategorije.equals("idTrazilica")){
                    intent = new Intent(context.getApplicationContext(), IzbornikPotkategorijaActivity.class);
                    intent.putExtra("idKategorije", kategorija.idKategorije);}
                else{
                     intent = new Intent(context.getApplicationContext(), TrazilicaActivity.class);
                }
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kategorijaArrayList.size();
    }

    public static class MyViewHolderKategorija extends RecyclerView.ViewHolder{

        ImageView slikaKategorije;
        TextView nazivKategorije;

        public MyViewHolderKategorija(@NonNull View itemView) {
            super(itemView);

            slikaKategorije = itemView.findViewById(R.id.slikaKategorije);
            nazivKategorije = itemView.findViewById(R.id.nazivKategorije);
        }
    }

}

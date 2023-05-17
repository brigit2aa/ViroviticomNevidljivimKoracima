package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
        View v = LayoutInflater.from(context).inflate(R.layout.objekti_potkategorije, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Ustanova ustanova = list.get(position);
        holder.naziv.setText(ustanova.getNaziv());
        holder.adresa.setText(ustanova.getAdresa());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), PodaciObjektaActivity.class);
                intent.putExtra("naziv", ustanova.getNaziv());
                intent.putExtra("adresa", ustanova.getAdresa());
                intent.putExtra("email" , ustanova.getEmail());
                intent.putExtra("telefon", ustanova.getTelefon());
                intent.putExtra("web", ustanova.getWeb());
                intent.putExtra("dodatnaInformacija", ustanova.getDodatnaInformacija());
                intent.putExtra("opisPraga", ustanova.getOpisPraga());
                intent.putExtra("opisUlaza", ustanova.getOpisUlaza());
                intent.putExtra("sirinaVrata", ustanova.getSirinaVrata());
                intent.putExtra("sirinaVrataUnutra", ustanova.getSirinaVrataUnutra());
                intent.putExtra("idVrata", ustanova.getIdVrata());
                intent.putExtra("opisPragaSlika", ustanova.getOpisPragaSlika());
                intent.putExtra("opisUlazaSlika", ustanova.getOpisUlazaSlika());
                intent.putExtra("sirinaVrataSlika", ustanova.getSirinaVrataSlika());
                intent.putExtra("dodatnaInformacijaSlika", ustanova.getDodatnaInformacijaSlika());
                intent.putExtra("sirinaVrataUnutraSlika", ustanova.getSirinaVrataUnutraSlika());
                intent.putExtra("vrstaVrataSlika", ustanova.getVrstaVrataSlika());
                intent.putExtra("slikaUlaza", ustanova.getSlikaUlaza());
                intent.putExtra("karta", ustanova.getKarta());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView naziv, adresa;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.naziv);
            adresa = itemView.findViewById(R.id.adresa);
        }
    }
}

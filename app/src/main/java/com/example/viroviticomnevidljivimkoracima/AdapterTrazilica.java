package com.example.viroviticomnevidljivimkoracima;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTrazilica extends RecyclerView.Adapter<AdapterTrazilica.MyViewHolderTrazilica> {

    Context context;
    ArrayList<Ustanova> ustanovaArrayList;

    public AdapterTrazilica(Context context, ArrayList<Ustanova> ustanovaArrayList) {
        this.context = context;
        this.ustanovaArrayList = ustanovaArrayList;
    }

    public void setFilterList(ArrayList<Ustanova> filterList){
        this.ustanovaArrayList = filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolderTrazilica onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new AdapterTrazilica.MyViewHolderTrazilica(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderTrazilica holder, int position) {

        Ustanova ustanova = ustanovaArrayList.get(position);
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
                intent.putExtra("vrstaVrata", ustanova.getIdVrata());
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
        return ustanovaArrayList.size();
    }

    public static class  MyViewHolderTrazilica extends RecyclerView.ViewHolder {
        TextView naziv, adresa;

        public MyViewHolderTrazilica(@NonNull View itemView) {
            super(itemView);

            naziv = itemView.findViewById(R.id.naziv);
            adresa = itemView.findViewById(R.id.adresa);
        }
    }
}

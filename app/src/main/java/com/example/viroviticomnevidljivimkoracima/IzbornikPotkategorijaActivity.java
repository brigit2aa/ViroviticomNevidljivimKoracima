package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IzbornikPotkategorijaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterPotkategorija adapterPotkategorija;
    ArrayList<Potkategorija> potkategorijaArrayList;
    ArrayList<Potkategorija> potkategorijaArrayListPom;
    private Button kategorijeGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_potkategorija);

        recyclerView = findViewById(R.id.menu_potkategorija);
        //Dohvat podatka. Ako imam više podataka koje prosljeđujem dohvatim si samo jedan objekt u Bundle.
        String kategorija=getIntent().getExtras().getSerializable("idKategorije").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("potkategorija");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        potkategorijaArrayList = new ArrayList<>();
        potkategorijaArrayListPom = new ArrayList<>();
        adapterPotkategorija = new AdapterPotkategorija(this, potkategorijaArrayList);
        recyclerView.setAdapter(adapterPotkategorija);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Potkategorija potkategorija = dataSnapshot.getValue(Potkategorija.class);
                    potkategorijaArrayListPom.add(potkategorija);
                }
                for (Potkategorija pot : potkategorijaArrayListPom){
                    if(pot.idKategorije.equals(kategorija)){
                        potkategorijaArrayList.add(pot);
                    }
                }
                adapterPotkategorija.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        kategorijeGumb = (Button) findViewById(R.id.kategorijeGumb);
        kategorijeGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                povratakNaKategorije();
            }
        });

    }

    private void povratakNaKategorije() {
        Intent intent = new Intent(this, IzbornikActivity.class);
        startActivity(intent);
    }
}

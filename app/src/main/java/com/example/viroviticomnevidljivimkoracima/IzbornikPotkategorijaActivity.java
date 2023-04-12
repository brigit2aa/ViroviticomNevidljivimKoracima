package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik_potkategorija);

        recyclerView = findViewById(R.id.menu_potkategorija);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("potkategorija");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        potkategorijaArrayList = new ArrayList<>();
        adapterPotkategorija = new AdapterPotkategorija(this, potkategorijaArrayList);
        recyclerView.setAdapter(adapterPotkategorija);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Potkategorija potkategorija = dataSnapshot.getValue(Potkategorija.class);
                    potkategorijaArrayList.add(potkategorija);
                }
                adapterPotkategorija.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
//firebase get child of id on click recycle view activity android studio java
//želim otvoriti kod kategorije potkategoriju
//https://www.youtube.com/watch?v=9_uLZdS_K_g
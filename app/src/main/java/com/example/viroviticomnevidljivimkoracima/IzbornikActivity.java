package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IzbornikActivity extends AppCompatActivity {
    private Button dodajUstanovuGumb;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterKategorija adapterKategorija;
    ArrayList<Kategorija> kategorijaArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik);

        recyclerView = findViewById(R.id.menu);
        databaseReference = FirebaseDatabase.getInstance().getReference("kategorija");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
//
        kategorijaArrayList = new ArrayList<>();
        adapterKategorija = new AdapterKategorija(this, kategorijaArrayList);
        recyclerView.setAdapter(adapterKategorija);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Kategorija kategorija = dataSnapshot.getValue(Kategorija.class);
                    kategorijaArrayList.add(kategorija);
                }
                adapterKategorija.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dodajUstanovuGumb = (Button) findViewById(R.id.dodajUstanovuGumb);
        dodajUstanovuGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {otvoriDodajUstanovuActivity();


            }
        });
    }

    private void otvoriDodajUstanovuActivity() {
        Intent intent = new Intent(this, DodavanjeUstanoveActivity.class);
        startActivity(intent);
    }
}
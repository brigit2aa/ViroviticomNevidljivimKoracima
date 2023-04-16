package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ObjektiPotkategorijeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter adapterObjekata;
    ArrayList<Ustanova> ustanovaArrayList;
    ArrayList<Ustanova> ustanovaArrayListPom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objekti_potkategorije);

        recyclerView = findViewById(R.id.objekti_potkategorije);
        String potkategorija = getIntent().getExtras().getSerializable("idPotkategorije").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ustanova");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ustanovaArrayList = new ArrayList<>();
        ustanovaArrayListPom = new ArrayList<>();
        adapterObjekata = new MyAdapter(this, ustanovaArrayList);
        recyclerView.setAdapter(adapterObjekata);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Ustanova ustanova = dataSnapshot.getValue(Ustanova.class);
                    ustanovaArrayListPom.add(ustanova);
                }
                for (Ustanova ust : ustanovaArrayListPom){
                    if(ust.idPotkategorije.equals(potkategorija)){
                        ustanovaArrayList.add(ust);
                    }
                }
                adapterObjekata.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
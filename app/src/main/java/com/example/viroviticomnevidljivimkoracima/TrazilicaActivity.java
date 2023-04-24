package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

public class TrazilicaActivity extends AppCompatActivity {

   RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterTrazilica adapterTrailica;
    ArrayList<Ustanova> ustanovaArrayList;
    private Button kategorijeGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazilica);

       recyclerView = findViewById(R.id.objekti_potkategorije);
        databaseReference = FirebaseDatabase.getInstance().getReference("ustanova");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ustanovaArrayList = new ArrayList<>();
        adapterTrailica = new AdapterTrazilica(this, ustanovaArrayList);
        recyclerView.setAdapter(adapterTrailica);
        //Staggered grid u 2 stupca
       // recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Ustanova ustanova = dataSnapshot.getValue(Ustanova.class);
                    ustanovaArrayList.add(ustanova);
                }
                adapterTrailica.notifyDataSetChanged();
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

package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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
    private ImageButton kategorijeGumb;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazilica);

       recyclerView = findViewById(R.id.objekti_potkategorije);

       searchView = findViewById(R.id.trazilica);
       searchView.clearFocus();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               filterList(newText);
               return true;
           }
       });

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

        kategorijeGumb = (ImageButton) findViewById(R.id.kategorijeGumb);
        kategorijeGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                povratakNaKategorije();
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Ustanova> filterList = new ArrayList<>();
        for (Ustanova ustanova : ustanovaArrayList){
            if(ustanova.getNaziv().toLowerCase().contains(text.toLowerCase())){
                filterList.add(ustanova);
            }
        }

        if (filterList.isEmpty()){
            Toast.makeText(this, "Ustanova nije pronađena.", Toast.LENGTH_SHORT).show();
        }
        else {
            adapterTrailica.setFilterList(filterList);
        }
    }

    private void povratakNaKategorije() {
        Intent intent = new Intent(this, IzbornikActivity.class);
        startActivity(intent);
    }
}

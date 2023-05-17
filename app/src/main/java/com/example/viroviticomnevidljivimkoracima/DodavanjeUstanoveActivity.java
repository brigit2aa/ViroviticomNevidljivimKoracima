package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DodavanjeUstanoveActivity extends AppCompatActivity {

    Spinner spinnerPotkategorija, spinnerOpisUlaza, spinnerSirinaVrata, spinnerVrstaVrata, spinnerOpisPraga, spinnerSirinaVrataUnutarUstanove;
    ImageButton dodajUstanovuGumb;
    EditText dodatnaInformacijatxt, emailtxt, telefontxt, webtxt, slikatxt, kartatxt, nazivtxt, adresatxt;
    DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReferenceInsert;
    AdapterVrstaVrata adapterVrstaVrata;
    ArrayList<VrstaVrata> vrataArrayList;
    AdapterOpisPraga adapterOpisPraga;
    ArrayList<OpisPraga> opisPragaArrayList;
    AdapterSirinaVrata adapterSirinaVrata;
    ArrayList<SirinaVrata> sirinaVrataArrayList;
    AdapterPotkategorijaBase adapterPotkategorijaBase;
    ArrayList<Potkategorija> potkategorijaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_ustanove);

        dodatnaInformacijatxt = (EditText) findViewById(R.id.dodatnaInformacija);
        emailtxt = (EditText) findViewById(R.id.email);
        telefontxt = (EditText) findViewById(R.id.telefon);
        webtxt = (EditText) findViewById(R.id.web);
        slikatxt = (EditText) findViewById(R.id.slika);
        kartatxt = (EditText) findViewById(R.id.karta);
        nazivtxt = (EditText) findViewById(R.id.naziv);
        adresatxt = (EditText) findViewById(R.id.adresa);

        spinnerPotkategorija = findViewById(R.id.spinerPotkategorija);
        spinnerVrstaVrata = findViewById(R.id.spinerVrstaVrata);
        spinnerOpisUlaza = findViewById(R.id.spinerUlazUObjekt);
        spinnerOpisPraga = findViewById(R.id.spinerUlazUObjektUnutra);
        spinnerSirinaVrata = findViewById(R.id.spinerSirinaVrata);
        spinnerSirinaVrataUnutarUstanove = findViewById(R.id.spinerSirinaVrataUnutra);

        databaseReference = FirebaseDatabase.getInstance().getReference("sifrarnik");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("OpisPraga");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("sirinaVrata");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("kategorija");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("potkategorija");


        vrataArrayList = new ArrayList<>();
        adapterVrstaVrata = new AdapterVrstaVrata(this, vrataArrayList);
        spinnerVrstaVrata.setAdapter(adapterVrstaVrata);

        opisPragaArrayList = new ArrayList<>();
        adapterOpisPraga = new AdapterOpisPraga(this, opisPragaArrayList);
        spinnerOpisUlaza.setAdapter(adapterOpisPraga);
        spinnerOpisPraga.setAdapter(adapterOpisPraga);

        sirinaVrataArrayList = new ArrayList<>();
        adapterSirinaVrata = new AdapterSirinaVrata(this, sirinaVrataArrayList);
        spinnerSirinaVrata.setAdapter(adapterSirinaVrata);
        spinnerSirinaVrataUnutarUstanove.setAdapter(adapterSirinaVrata);


        potkategorijaArrayList = new ArrayList<>();
        adapterPotkategorijaBase = new AdapterPotkategorijaBase(this, potkategorijaArrayList);
        spinnerPotkategorija.setAdapter(adapterPotkategorijaBase);
        //spinnerPotkategorija.setPrompt("Odaberi");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    VrstaVrata vrstaVrata = dataSnapshot.getValue(VrstaVrata.class);

                    vrataArrayList.add(vrstaVrata);
                }
                adapterVrstaVrata.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    OpisPraga opisPraga = dataSnapshot1.getValue(OpisPraga.class);
                    opisPragaArrayList.add(opisPraga);
                }
                adapterOpisPraga.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot2 : snapshot.getChildren()){
                    SirinaVrata sirinaVrata = dataSnapshot2.getValue(SirinaVrata.class);
                    sirinaVrataArrayList.add(sirinaVrata);
                }
                adapterSirinaVrata.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot4 : snapshot.getChildren()){
                    Potkategorija potkategorija = dataSnapshot4.getValue(Potkategorija.class);
                    potkategorijaArrayList.add(potkategorija);
                }

                adapterPotkategorijaBase.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        dodajUstanovuGumb = (ImageButton) findViewById(R.id.dodajUstanovuGumb);
        dodajUstanovuGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajNovuUstanovu();
            }
        });
    }

    private void dodajNovuUstanovu() {
        //String idPotkategorije = spinnerPotkategorija.getSelectedItem().toString();
        //String vrstaVrata = spinnerVrstaVrata.getSelectedItem().toString();
        String opisUlaza = spinnerOpisUlaza.getSelectedItem().toString();
        String opisPraga = spinnerOpisPraga.getSelectedItem().toString();
        String sirinaVrata = spinnerSirinaVrata.getSelectedItem().toString();
        String sirinaVrataUnutra = spinnerSirinaVrataUnutarUstanove.getSelectedItem().toString();
        String adresa = adresatxt.getText().toString();
        String email = emailtxt.getText().toString();
        String naziv = nazivtxt.getText().toString();
        String telefon = telefontxt.getText().toString();
        String web = webtxt.getText().toString();
        String dodatnaInformacija = dodatnaInformacijatxt.getText().toString();
        String karta = kartatxt.getText().toString();

        //if (!opisUlaza.isEmpty() && !opisPraga.isEmpty() && !sirinaVrata.isEmpty() && sirinaVrataUnutra.isEmpty()){
            Ustanova ustanova = new Ustanova(adresa, email, naziv, telefon, web, dodatnaInformacija, karta, opisPraga, opisUlaza, sirinaVrata, sirinaVrataUnutra);




            FirebaseDatabase.getInstance().getReference("ustanova").push().setValue(ustanova);
            adresatxt.setText("");
            emailtxt.setText("");
            nazivtxt.setText("");
            telefontxt.setText("");
            webtxt.setText("");
            dodatnaInformacijatxt.setText("");
            kartatxt.setText("");
            Toast.makeText(DodavanjeUstanoveActivity.this, "Ustanova je uspješno dodana!", Toast.LENGTH_SHORT).show();
        //}



    }
}
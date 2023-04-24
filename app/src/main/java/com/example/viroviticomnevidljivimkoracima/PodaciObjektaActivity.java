package com.example.viroviticomnevidljivimkoracima;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PodaciObjektaActivity extends AppCompatActivity {
    private TextView naziv, adresa, email, telefon, web, dodatnaInformacija, opisPraga, opisUlaza, sirinaVrata, sirinaVrataUnutra, vrstaVrata;
    private  ImageView opisPragaSlika, opisUlazaSlika, sirinaVrataSlika, dodatnaInformacijaSlika, sirinaVrataUnutraSlika, vrstaVrataSlika;
    private Button kategorijeGumb, nazadeGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaci_objekta);

        naziv = (TextView) findViewById(R.id.naziv);
        adresa = (TextView) findViewById(R.id.adresa);
        email = (TextView) findViewById(R.id.email);
        telefon = (TextView) findViewById(R.id.telefon);
        web = (TextView) findViewById(R.id.web);
        dodatnaInformacija = (TextView) findViewById(R.id.dodatnaInformacija);
        opisPraga = (TextView) findViewById(R.id.opisPraga);
        opisUlaza= (TextView) findViewById(R.id.opisUlaza);
        sirinaVrata = (TextView) findViewById(R.id.sirinaVrata);
        sirinaVrataUnutra = (TextView) findViewById(R.id.sirinaVrataUnutra);
        vrstaVrata = (TextView) findViewById(R.id.vrstaVrata);
        dodatnaInformacijaSlika = (ImageView) findViewById(R.id.dodatnaInformacijaSlika);

        opisPragaSlika = (ImageView) findViewById(R.id.opisPragaSlika);
        opisUlazaSlika = (ImageView) findViewById(R.id.opisUlazaSlika);
        sirinaVrataSlika = (ImageView) findViewById(R.id.sirinaVrataSlika);
        sirinaVrataUnutraSlika = (ImageView) findViewById(R.id.sirinaVrataUnutraSlika);
        vrstaVrataSlika = (ImageView) findViewById(R.id.vrstaVrataSlika);

        naziv.setText(getIntent().getStringExtra("naziv"));
        adresa.setText(getIntent().getStringExtra("adresa"));
        email.setText(getIntent().getStringExtra("email"));
        telefon.setText(getIntent().getStringExtra("telefon"));
        web.setText(getIntent().getStringExtra("web"));
        dodatnaInformacija.setText(getIntent().getStringExtra("dodatnaInformacija"));
        opisPraga.setText(getIntent().getStringExtra("opisPraga"));
        opisUlaza.setText(getIntent().getStringExtra("opisUlaza"));
        sirinaVrata.setText(getIntent().getStringExtra("sirinaVrata"));
        sirinaVrataUnutra.setText(getIntent().getStringExtra("sirinaVrataUnutra"));
        vrstaVrata.setText(getIntent().getStringExtra("vrstaVrata"));

        Picasso.get().load(getIntent().getStringExtra("dodatnaInformacijaSlika")).into(dodatnaInformacijaSlika);
        Picasso.get().load(getIntent().getStringExtra("opisPragaSlika")).into(opisPragaSlika);
        Picasso.get().load(getIntent().getStringExtra("opisUlazaSlika")).into(opisUlazaSlika);
        Picasso.get().load(getIntent().getStringExtra("sirinaVrataSlika")).into(sirinaVrataSlika);
        Picasso.get().load(getIntent().getStringExtra("sirinaVrataUnutraSlika")).into(sirinaVrataUnutraSlika);
        Picasso.get().load(getIntent().getStringExtra("vrstaVrataSlika")).into(vrstaVrataSlika);

        nazadeGumb = (Button) findViewById(R.id.nazadeGumb);
        nazadeGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                povratakNaObjektePotkategorije();
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

    private void povratakNaObjektePotkategorije() {
        Intent intent = new Intent(this, ObjektiPotkategorijeActivity.class);
        startActivity(intent);
    }
}
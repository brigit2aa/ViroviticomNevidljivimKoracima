package com.example.viroviticomnevidljivimkoracima;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PodaciObjektaActivity extends AppCompatActivity {

    private TextView naziv, adresa, email, telefon, web, dodatnaInformacija, opisPraga, opisUlaza, sirinaVrata, sirinaVrataUnutra, vrstaVrata;
    private  ImageView opisPragaSlika, opisUlazaSlika, sirinaVrataSlika, dodatnaInformacijaSlika, sirinaVrataUnutraSlika, vrstaVrataSlika;
    private Button kategorijeGumb, nazadeGumb, slikaGumb, kartaGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podaci_objekta);

        //To sam dodala!
        //String tipVrata = getIntent().getExtras().getSerializable("sifrarnik").toString();

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
        //vrstaVrata.setText(tipVrata);tip vrata dodala
        Picasso.get().load(getIntent().getStringExtra("dodatnaInformacijaSlika")).into(dodatnaInformacijaSlika);
        Picasso.get().load(getIntent().getStringExtra("opisPragaSlika")).into(opisPragaSlika);
        Picasso.get().load(getIntent().getStringExtra("opisUlazaSlika")).into(opisUlazaSlika);
        Picasso.get().load(getIntent().getStringExtra("sirinaVrataSlika")).into(sirinaVrataSlika);
        Picasso.get().load(getIntent().getStringExtra("sirinaVrataUnutraSlika")).into(sirinaVrataUnutraSlika);
        Picasso.get().load(getIntent().getStringExtra("vrstaVrataSlika")).into(vrstaVrataSlika);

        slikaGumb = (Button) findViewById(R.id.slikaGumb);
        slikaGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prikaziSlikuUlaza();
            }
        });

        kartaGumb = (Button) findViewById(R.id.kartaGumb);
        kartaGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prikaziLokaciju();
            }
        });
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

    private void prikaziLokaciju() {

        LayoutInflater layoutInflaterLokacija = LayoutInflater.from(this);
        View lokacija = layoutInflaterLokacija.inflate(R.layout.lokacija, null);
        WebView katra;
        katra = (WebView) lokacija.findViewById(R.id.karta);
        katra.getSettings().setJavaScriptEnabled(true);
        katra.loadUrl(getIntent().getStringExtra("karta"));

    }

    private void prikaziSlikuUlaza() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View slikaUlazaDialog = layoutInflater.inflate(R.layout.dialog_slika_ulaza, null);
        ImageView slikaUlaza;
        Button zatvoriGumb;
        slikaUlaza = (ImageView) slikaUlazaDialog.findViewById(R.id.slikaUlaza);
        zatvoriGumb = (Button) slikaUlazaDialog.findViewById(R.id.zatvoriGumb);
        Picasso.get().load(getIntent().getStringExtra("slikaUlaza")).into(slikaUlaza);

        alertDialog.setView(slikaUlazaDialog);

        AlertDialog dialog = alertDialog.create();

        zatvoriGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
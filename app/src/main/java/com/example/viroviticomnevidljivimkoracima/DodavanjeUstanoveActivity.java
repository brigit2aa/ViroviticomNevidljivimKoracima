package com.example.viroviticomnevidljivimkoracima;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DodavanjeUstanoveActivity extends AppCompatActivity {

    Spinner spinnerPotkategorija, spinnerOpisUlaza, spinnerSirinaVrata, spinnerVrstaVrata, spinnerOpisPraga, spinnerSirinaVrataUnutarUstanove, spinnerSlikaDodatnaInformacija, spinerSlikaUlazUObjekt, spinerSlikaSirinaVrata, spinerSlikaVrstaVrata, spinerSlikaUlazUObjektUnutra, spinerSlikaSirinaVrataUnutra;
    ImageButton dodajUstanovuGumb;
    ImageButton dodajSliku;
    ImageView slika;
    EditText dodatnaInformacijatxt, emailtxt, telefontxt, webtxt, kartatxt, nazivtxt, adresatxt;
    DatabaseReference databaseReference, databaseReference1, databaseReference2, databaseReference3, databaseReference4, databaseReference5;
    AdapterVrstaVrata adapterVrstaVrata;
    ArrayList<VrstaVrata> vrataArrayList;
    AdapterOpisPraga adapterOpisPraga;
    ArrayList<OpisPraga> opisPragaArrayList;
    AdapterSirinaVrata adapterSirinaVrata;
    AdapterPrilagodbeBasic adapterPrilagodbeBasic;
    ArrayList<Prilagodba> prilagodbaArrayList;
    ArrayList<SirinaVrata> sirinaVrataArrayList;
    AdapterPotkategorijaBase adapterPotkategorijaBase;
    ArrayList<Potkategorija> potkategorijaArrayList2;

    private Uri imageUri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodavanje_ustanove);

        dodatnaInformacijatxt = (EditText) findViewById(R.id.dodatnaInformacija);
        emailtxt = (EditText) findViewById(R.id.email);
        telefontxt = (EditText) findViewById(R.id.telefon);
        webtxt = (EditText) findViewById(R.id.web);
        dodajSliku = (ImageButton) findViewById(R.id.slikaUcitaj);
        slika = (ImageView) findViewById(R.id.slika);
        kartatxt = (EditText) findViewById(R.id.karta);
        nazivtxt = (EditText) findViewById(R.id.naziv);
        adresatxt = (EditText) findViewById(R.id.adresa);

        spinnerPotkategorija = findViewById(R.id.spinerPotkategorija);
        spinnerVrstaVrata = findViewById(R.id.spinerVrstaVrata);
        spinnerOpisUlaza = findViewById(R.id.spinerUlazUObjekt);
        spinnerOpisPraga = findViewById(R.id.spinerUlazUObjektUnutra);
        spinnerSirinaVrata = findViewById(R.id.spinerSirinaVrata);
        spinnerSirinaVrataUnutarUstanove = findViewById(R.id.spinerSirinaVrataUnutra);
        spinnerSlikaDodatnaInformacija = findViewById(R.id.spinerDodatnaInformacija);
        //Sad dodala!
        spinerSlikaUlazUObjekt = findViewById(R.id.spinerSlikaUlazUObjekt);
        spinerSlikaSirinaVrata = findViewById(R.id.spinerSlikaSirinaVrata);
        spinerSlikaVrstaVrata = findViewById(R.id.spinerSlikaVrstaVrata);
        spinerSlikaUlazUObjektUnutra = findViewById(R.id.spinerSlikaUlazUObjektUnutra);
        spinerSlikaSirinaVrataUnutra = findViewById(R.id.spinerSlikaSirinaVrataUnutra);
        //
        databaseReference = FirebaseDatabase.getInstance().getReference("sifrarnik");
        databaseReference1 = FirebaseDatabase.getInstance().getReference("OpisPraga");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("sirinaVrata");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("kategorija");
        databaseReference4 = FirebaseDatabase.getInstance().getReference("potkategorija");
        databaseReference5 = FirebaseDatabase.getInstance().getReference("prilagodba");
        storageReference = FirebaseStorage.getInstance().getReference();


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

        potkategorijaArrayList2 = new ArrayList<>();
        adapterPotkategorijaBase = new AdapterPotkategorijaBase(this, potkategorijaArrayList2);
        spinnerPotkategorija.setAdapter(adapterPotkategorijaBase);
        //spinnerPotkategorija.setPrompt("Odaberi");

        prilagodbaArrayList = new ArrayList<>();
        adapterPrilagodbeBasic = new AdapterPrilagodbeBasic(this, prilagodbaArrayList);
        spinnerSlikaDodatnaInformacija.setAdapter(adapterPrilagodbeBasic);
        spinerSlikaUlazUObjekt.setAdapter(adapterPrilagodbeBasic);
        spinerSlikaSirinaVrata.setAdapter(adapterPrilagodbeBasic);
        spinerSlikaVrstaVrata.setAdapter(adapterPrilagodbeBasic);
        spinerSlikaUlazUObjektUnutra.setAdapter(adapterPrilagodbeBasic);
        spinerSlikaSirinaVrataUnutra.setAdapter(adapterPrilagodbeBasic);

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
                    potkategorijaArrayList2.add(potkategorija);
                }

                adapterPotkategorijaBase.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot5 : snapshot.getChildren()){
                    Prilagodba prilagodba = dataSnapshot5.getValue(Prilagodba.class);
                    prilagodbaArrayList.add(prilagodba);
                }
                adapterPrilagodbeBasic.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dodajSliku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ucitajSliku();

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

    private void ucitajSliku() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){
            imageUri = data.getData();
            slika.setImageURI(imageUri);

        }
    }

    private void dodajNovuUstanovu() {

        if (adresatxt.getText().toString().isEmpty() || emailtxt.getText().toString().isEmpty() || nazivtxt.getText().toString().isEmpty() || telefontxt.getText().toString().isEmpty() || webtxt.getText().toString().isEmpty() || kartatxt.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Upišite podatke.", Toast.LENGTH_SHORT).show();
        }
        else {
            StorageReference reference = storageReference.child("slike").child(System.currentTimeMillis() + "");
            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            UstanovaDodavanje ustanovaDodavanje2 = new UstanovaDodavanje();
                            ustanovaDodavanje2.setSlikaUlaza(uri.toString());
                            ustanovaDodavanje2.setIdPotkategorije(spinnerPotkategorija.getSelectedItem().toString());
                            ustanovaDodavanje2.setIdVrata(spinnerVrstaVrata.getSelectedItem().toString());
                            ustanovaDodavanje2.setOpisUlaza(spinnerOpisUlaza.getSelectedItem().toString());
                            ustanovaDodavanje2.setOpisUlazaSlika(spinerSlikaUlazUObjekt.getSelectedItem().toString());//taj sam popravila
                            ustanovaDodavanje2.setOpisPraga(spinnerOpisPraga.getSelectedItem().toString());
                            ustanovaDodavanje2.setOpisPragaSlika(spinerSlikaUlazUObjektUnutra.getSelectedItem().toString());//i taj sam
                            ustanovaDodavanje2.setSirinaVrataSlika(spinerSlikaSirinaVrata.getSelectedItem().toString());
                            ustanovaDodavanje2.setVrstaVrataSlika(spinerSlikaVrstaVrata.getSelectedItem().toString());
                            ustanovaDodavanje2.setSirinaVrataUnutraSlika(spinerSlikaSirinaVrataUnutra.getSelectedItem().toString());
                            ustanovaDodavanje2.setSirinaVrata(spinnerSirinaVrata.getSelectedItem().toString());
                            ustanovaDodavanje2.setSirinaVrataUnutra(spinnerSirinaVrataUnutarUstanove.getSelectedItem().toString());
                            ustanovaDodavanje2.setDodatnaInformacijaSlika(spinnerSlikaDodatnaInformacija.getSelectedItem().toString());

                            ustanovaDodavanje2.setAdresa(adresatxt.getText().toString());
                            ustanovaDodavanje2.setEmail(emailtxt.getText().toString());
                            ustanovaDodavanje2.setNaziv(nazivtxt.getText().toString());
                            ustanovaDodavanje2.setTelefon(telefontxt.getText().toString());
                            ustanovaDodavanje2.setWeb(webtxt.getText().toString());
                            ustanovaDodavanje2.setDodatnaInformacija(dodatnaInformacijatxt.getText().toString());
                            ustanovaDodavanje2.setKarta(kartatxt.getText().toString());


                            FirebaseDatabase.getInstance().getReference("ustanova").push().setValue(ustanovaDodavanje2);
                            adresatxt.setText("");
                            emailtxt.setText("");
                            nazivtxt.setText("");
                            telefontxt.setText("");
                            webtxt.setText("");
                            dodatnaInformacijatxt.setText("");
                            kartatxt.setText("");

                        }
                    });
                }
            });

            Toast.makeText(DodavanjeUstanoveActivity.this, "Ustanova je uspješno dodana!", Toast.LENGTH_SHORT).show();

         ///////
            String title = "Viroviticom nevidljivim koracima";
            String message = "U aplikaciju je dodan novi objekt na području grada Virovitice";
            FCMSend.pushNotification(
                    DodavanjeUstanoveActivity.this,
                    "e6i3f6DXQnSArkJ-7HapWk:APA91bHnwtCS-qlLDpBV9simRqrA8flX5dicmE6FgwtHX5Zlu79PSdCIrl4pMG91JLNy9foHQPb9aCJIosRCjD99o6-gRR-Me0kxfeQFi3sRl55oSmD3cgp-CUfHjHvpNWNiKqVu6rEN",
                    title,
                    message);
          /////
            /*FirebaseMessaging.getInstance().subscribeToTopic("weather")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            String msg = "Subscribed";
                            if (!task.isSuccessful()) {
                                msg = "Subscribe failed";
                            }
                            Log.d(TAG, msg);
                            Toast.makeText(DodavanjeUstanoveActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });*/////////////////
            /*ComponentName componentName = new ComponentName(this, FCMSend.class);
            getPackageManager().setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP);*/

            ////////////////////
            Intent intent = new Intent(this, PodaciObjektaActivity.class);
                intent.putExtra("naziv", nazivtxt.getText().toString());
                intent.putExtra("adresa", adresatxt.getText().toString());
                intent.putExtra("email", emailtxt.getText().toString());
                intent.putExtra("telefon", telefontxt.getText().toString());
                intent.putExtra("web", webtxt.getText().toString());
                intent.putExtra("karta", kartatxt.getText().toString());
                intent.putExtra("dodatnaInformacija", dodatnaInformacijatxt.getText().toString());
                intent.putExtra("opisPraga", spinnerOpisPraga.getSelectedItem().toString());
                intent.putExtra("opisUlaza", spinnerOpisUlaza.getSelectedItem().toString());
                intent.putExtra("sirinaVrata", spinnerSirinaVrata.getSelectedItem().toString());
                intent.putExtra("sirinaVrataUnutra", spinnerSirinaVrataUnutarUstanove.getSelectedItem().toString());
                intent.putExtra("idVrata",spinnerVrstaVrata.getSelectedItem().toString());
                intent.putExtra("dodatnaInformacijaSlika", spinnerSlikaDodatnaInformacija.getSelectedItem().toString());
                intent.putExtra("opisPragaSlika", spinerSlikaUlazUObjektUnutra.getSelectedItem().toString());
                intent.putExtra("opisUlazaSlika",spinerSlikaUlazUObjekt.getSelectedItem().toString());
                intent.putExtra("sirinaVrataSlika", spinerSlikaSirinaVrata.getSelectedItem().toString());
                intent.putExtra("sirinaVrataUnutraSlika", spinerSlikaSirinaVrataUnutra.getSelectedItem().toString());
                intent.putExtra("vrstaVrataSlika", spinerSlikaVrstaVrata.getSelectedItem().toString());
                intent.putExtra("slikaUlaza", imageUri.toString());

            startActivity(intent);

           /* FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                return;
                            }

                            String token = task.getResult();

                            System.out.println("TOKEN " + token);
                        }
                    });*/

        }
    }
}
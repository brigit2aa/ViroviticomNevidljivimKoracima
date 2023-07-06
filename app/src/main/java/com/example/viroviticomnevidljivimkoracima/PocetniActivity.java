package com.example.viroviticomnevidljivimkoracima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PocetniActivity extends AppCompatActivity {

    private Button kreniGumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);

        kreniGumb = (Button) findViewById(R.id.kreniGumb);
        kreniGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otvoriGlavniActiviry();
            }
        });

    }

    private void otvoriGlavniActiviry() {
        Intent intent = new Intent(this, IzbornikActivity.class);
        startActivity(intent);

    }
}

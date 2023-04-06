package com.example.viroviticomnevidljivimkoracima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class IzbornikActivity extends AppCompatActivity {
    private ImageButton trazilicaGumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik);

        trazilicaGumb = (ImageButton) findViewById(R.id.button9);
        trazilicaGumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {otvoriTrazilicuActivity();

            }
        });
    }

    private void otvoriTrazilicuActivity() {
        Intent intent = new Intent(this, TrazilicaActivity.class);
        startActivity(intent);
    }
}
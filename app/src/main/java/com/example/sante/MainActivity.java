package com.example.sante;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnRechercheMedicament, btnMesOrdonnances, btnRendezVous;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRechercheMedicament = findViewById(R.id.btnRechercheMedicament);
        btnMesOrdonnances = findViewById(R.id.btnMesOrdonnances);
        btnRendezVous = findViewById(R.id.btnRendezVous);

        btnRechercheMedicament.setOnClickListener(v -> {
            Intent intent = new Intent(this, RechercheMedicamentActivity.class);
            startActivity(intent);
        });

        btnMesOrdonnances.setOnClickListener(v -> {
            Intent intent = new Intent(this, MesOrdonnancesActivity.class);
            startActivity(intent);
        });

        // 📅 Accéder à la gestion des rendez-vous (CRUD)
        btnRendezVous.setOnClickListener(v -> {
            Intent intent = new Intent(this, RendezVousActivity.class);
            startActivity(intent);
        });
    }
}
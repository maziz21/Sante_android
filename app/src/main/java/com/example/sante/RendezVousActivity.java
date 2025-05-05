package com.example.sante;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




public class RendezVousActivity extends AppCompatActivity {

    private RendezVousAdapter adapter;
    private RendezVousViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rendezvous);

        Button btnAjout = findViewById(R.id.btnAjoutRdv);
        Button btnAfficher = findViewById(R.id.btnGetRdv);
        RecyclerView recyclerView = findViewById(R.id.recyclerRdv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RendezVousAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(RendezVousViewModel.class);

        btnAfficher.setOnClickListener(v -> {
            viewModel.getAllRendezVous().observe(this, adapter::setData);
        });

        btnAjout.setOnClickListener(v -> {
            Intent intent = new Intent(this, AjouterRendezVousActivity.class);
            startActivity(intent);
        });

        adapter.setOnDeleteClickListener(id -> {
            new AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Supprimer ce rendez-vous ?")
                    .setPositiveButton("Oui", (dialog, which) -> viewModel.deleteById(id))
                    .setNegativeButton("Non", null)
                    .show();
        });
    }
}

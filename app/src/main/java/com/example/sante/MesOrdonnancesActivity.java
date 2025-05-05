package com.example.sante;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MesOrdonnancesActivity extends AppCompatActivity {

    private OrdonnanceViewModel viewModel;
    private OrdonnanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_ordonnances);

        RecyclerView recyclerView = findViewById(R.id.recyclerOrdonnances);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new OrdonnanceAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(OrdonnanceViewModel.class);
        viewModel.getAll().observe(this, adapter::setData);
    }
}


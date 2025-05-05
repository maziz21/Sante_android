package com.example.sante;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RechercheMedicamentActivity extends AppCompatActivity {

    private EditText etRecherche;
    private MedicamentAdapter adapter;
    private OrdonnanceViewModel ordonnanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_medicament);

        etRecherche = findViewById(R.id.etRecherche);
        Button btnRechercher = findViewById(R.id.btnRechercher);
        RecyclerView recyclerView = findViewById(R.id.recyclerMedicaments);

        adapter = new MedicamentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ordonnanceViewModel = new ViewModelProvider(this).get(OrdonnanceViewModel.class);

        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        OpenFDAApi api = retrofit.create(OpenFDAApi.class);

        btnRechercher.setOnClickListener(v -> {
            String query = etRecherche.getText().toString().trim();
            api.getMedicaments(query, 10).enqueue(new Callback<MedicamentResponse>() {
                @Override
                public void onResponse(Call<MedicamentResponse> call, Response<MedicamentResponse> response) {
                    if (response.isSuccessful()) {
                        MedicamentResponse medicamentResponse = response.body();
                        if (medicamentResponse != null && medicamentResponse.results != null) {
                            adapter.setMedicaments(medicamentResponse.results);
                        } else {
                            Toast.makeText(RechercheMedicamentActivity.this, "Aucun médicament trouvé", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RechercheMedicamentActivity.this, "Erreur réponse API", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MedicamentResponse> call, Throwable t) {
                    Log.e("RechercheMedicament", "Erreur API : ", t);
                    Toast.makeText(RechercheMedicamentActivity.this, "Erreur API", Toast.LENGTH_SHORT).show();
                }
            });
        });

        adapter.setOnItemClickListener(medicament -> {
            Ordonnance ordonnance = new Ordonnance();
            ordonnance.nomMedicament = medicament.openFDA.brandName != null ? medicament.openFDA.brandName.get(0) : "Inconnu";
            ordonnanceViewModel.insert(ordonnance);
            Toast.makeText(this, "Ajouté à vos ordonnances", Toast.LENGTH_SHORT).show();
        });
    }
}

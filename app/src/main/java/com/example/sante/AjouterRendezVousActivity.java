package com.example.sante;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.Manifest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;



public class AjouterRendezVousActivity extends AppCompatActivity {

    private EditText etDate, etHeure, etMedecin, etLieu;
    private RendezVousViewModel viewModel;

    private static final String CHANNEL_ID = "rdv_channel";
    private static final int NOTIF_ID = 1;
    private static final int REQ_CODE_NOTIF = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_rendezvous);


        etDate = findViewById(R.id.etDate);
        etHeure = findViewById(R.id.etHeure);
        etMedecin = findViewById(R.id.etMedecin);
        etLieu = findViewById(R.id.etLieu);
        Button btnEnregistrer = findViewById(R.id.btnEnregistrer);

        viewModel = new ViewModelProvider(this).get(RendezVousViewModel.class);

        btnEnregistrer.setOnClickListener(v -> {
            String date = etDate.getText().toString().trim();
            String heure = etHeure.getText().toString().trim();
            String medecin = etMedecin.getText().toString().trim();
            String lieu = etLieu.getText().toString().trim();

            if (date.isEmpty() || heure.isEmpty() || medecin.isEmpty() || lieu.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {

                RendezVous rdv = new RendezVous();
                rdv.date = date;
                rdv.heure = heure;
                rdv.medecin = medecin;
                rdv.lieu = lieu;

                viewModel.insert(rdv);
                demanderPermissionEtNotifier();  // Gérer la notification
                finish(); // Revenir à l'écran précédent
            }
        });
    }

    private void demanderPermissionEtNotifier() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQ_CODE_NOTIF);
                return;
            }
        }
        afficherNotification();
    }

    private void afficherNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Canal de rendez-vous",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            manager.createNotificationChannel(channel);
        }

        // Créer et afficher la notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // à remplacer par ton icône
                .setContentTitle("Rendez-vous ajouté")
                .setContentText("Votre rendez-vous a été enregistré avec succès.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        manager.notify(NOTIF_ID, builder.build());
    }

    // Résultat de la demande de permission
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQ_CODE_NOTIF) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                afficherNotification();
            } else {
                Toast.makeText(this, "Permission de notification refusée", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

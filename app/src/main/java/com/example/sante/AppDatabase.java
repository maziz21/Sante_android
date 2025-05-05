package com.example.sante;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {Ordonnance.class, RendezVous.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract OrdonnanceDao ordonnanceDao();
    public abstract RendezVousDao rendezVousDao(); // si tu l'as

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "sante_db"
                    ).fallbackToDestructiveMigration() // 🔁 pour forcer recréation si besoin
                    .build();
        }
        return instance;
    }
}


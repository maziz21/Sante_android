package com.example.sante;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class OrdonnanceViewModel extends AndroidViewModel {
    private final OrdonnanceDao dao;
    private final LiveData<List<Ordonnance>> ordonnances;

    public OrdonnanceViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.ordonnanceDao();
        ordonnances = dao.getAll();
    }

    public LiveData<List<Ordonnance>> getAll() {
        return ordonnances;
    }

    public void insert(Ordonnance ordonnance) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(ordonnance));
    }
}


package com.example.sante;
import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class Repository {
    private final RendezVousDao dao;
    private final LiveData<List<RendezVous>> allRendezVous;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.rendezVousDao();
        allRendezVous = dao.getAll();
    }

    public LiveData<List<RendezVous>> getAllRendezVous() {
        return allRendezVous;
    }

    public void insert(RendezVous rdv) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(rdv));
    }

    public void delete(RendezVous rdv) {
        Executors.newSingleThreadExecutor().execute(() -> dao.delete(rdv));
    }

    public void deleteById(int id) {
        Executors.newSingleThreadExecutor().execute(() -> dao.deleteById(id));
    }
}
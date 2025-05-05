package com.example.sante;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class RendezVousViewModel extends AndroidViewModel {
    private final Repository repository;
    private final LiveData<List<RendezVous>> allRendezVous;

    public RendezVousViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allRendezVous = repository.getAllRendezVous();
    }

    public LiveData<List<RendezVous>> getAllRendezVous() {
        return allRendezVous;
    }

    public void insert(RendezVous rdv) {
        repository.insert(rdv);
    }

    public void delete(RendezVous rdv) {
        repository.delete(rdv);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
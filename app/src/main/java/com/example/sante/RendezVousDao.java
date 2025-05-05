package com.example.sante;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;


@Dao
public interface RendezVousDao {
    @Insert
    void insert(RendezVous rendezVous);

    @Delete
    void delete(RendezVous rendezVous);

    @Query("SELECT * FROM rendezvous ORDER BY date DESC")
    LiveData<List<RendezVous>> getAll();

    @Query("DELETE FROM rendezvous WHERE id = :id")
    void deleteById(int id);
}

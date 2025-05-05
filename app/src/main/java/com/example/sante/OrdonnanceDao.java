package com.example.sante;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrdonnanceDao {
    @Insert
    void insert(Ordonnance ordonnance);

    @Query("SELECT * FROM Ordonnance ORDER BY id DESC")
    LiveData<List<Ordonnance>> getAll();
}

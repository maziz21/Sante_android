package com.example.sante;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ordonnance {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nomMedicament;
}


package com.example.sante;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rendezvous")
public class RendezVous {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String date;
    public String heure;
    public String medecin;
    public String lieu;
}


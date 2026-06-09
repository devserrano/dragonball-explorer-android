package com.devserrano.dragonballexplorer.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_characters")
public class FavoriteCharacter {

    @PrimaryKey
    public int id;

    @NonNull
    public String name;

    @NonNull
    public String race;

    @NonNull
    public String ki;

    @NonNull
    public String image;

    @NonNull
    public String note;

    public FavoriteCharacter(
            int id,
            @NonNull String name,
            @NonNull String race,
            @NonNull String ki,
            @NonNull String image,
            @NonNull String note
    ) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.ki = ki;
        this.image = image;
        this.note = note;
    }
}
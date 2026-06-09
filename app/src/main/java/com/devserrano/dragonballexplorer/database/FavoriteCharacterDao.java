package com.devserrano.dragonballexplorer.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteCharacter favoriteCharacter);

    @Query("SELECT * FROM favorite_characters")
    List<FavoriteCharacter> getAllFavorites();

    @Delete
    void deleteFavorite(FavoriteCharacter favoriteCharacter);
}
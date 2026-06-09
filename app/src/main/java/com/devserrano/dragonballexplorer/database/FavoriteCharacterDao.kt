package com.devserrano.dragonballexplorer.database
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface FavoriteCharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteCharacter)

    @Query ("SELECT * FROM favorite_characters")
    suspend fun getAllFavorites(): List<FavoriteCharacter>

    @Update
    suspend fun updateFavorite(favorite: FavoriteCharacter)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteCharacter)

    @Query ("SELECT * FROM favorite_characters WHERE id = :id LIMIT 1")
    suspend fun getFavoriteByld(id: Int): FavoriteCharacter?

}
package com.devserrano.dragonballexplorer.database
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_characters")
data class FavoriteCharacter (

    @PrimaryKey
    val id: Int,
    val name: String,
    val race: String,
    val ki: String,
    val image: String,
    val note: String = ""


)



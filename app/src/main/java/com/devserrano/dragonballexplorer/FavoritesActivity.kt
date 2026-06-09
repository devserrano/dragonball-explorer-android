package com.devserrano.dragonballexplorer

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devserrano.dragonballexplorer.adapters.FavoriteAdapter
import kotlinx.coroutines.launch
import com.devserrano.dragonballexplorer.database.DatabaseProvider
import com.devserrano.dragonballexplorer.database.FavoriteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.app.AlertDialog

import android.widget.EditText


class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_favorites)

        val navCharacters = findViewById<TextView>(R.id.navCharacters)
        val recyclerFavorites = findViewById<RecyclerView>(R.id.recyclerFavorites)

        navCharacters.setOnClickListener {
            val intent = Intent(
                this@FavoritesActivity,
                MainActivity::class.java
            )

            startActivity(intent)
            finish()
        }

        recyclerFavorites.layoutManager = LinearLayoutManager(this)

        lateinit var adapter: FavoriteAdapter

        adapter = FavoriteAdapter(
            emptyList<FavoriteCharacter>(),
            onDeleteClick = { favorite ->

                lifecycleScope.launch {

                    val updatedFavorites = withContext(Dispatchers.IO) {

                        val database =
                            DatabaseProvider.getDatabase(this@FavoritesActivity)

                        database.favoriteCharacterDao()
                            .deleteFavorite(favorite)

                        database.favoriteCharacterDao()
                            .getAllFavorites()
                    }

                    adapter.updateList(updatedFavorites)
                }
            },
            onEditClick = { favorite ->

                val input = EditText(this@FavoritesActivity)
                input.setText(favorite.note)
                input.hint = "Escribe una nota"

                AlertDialog.Builder(this@FavoritesActivity)
                    .setTitle("Editar nota")
                    .setView(input)
                    .setPositiveButton("Guardar") { _, _ ->

                        favorite.note = input.text.toString()

                        lifecycleScope.launch {

                            val updatedFavorites = withContext(Dispatchers.IO) {

                                val database =
                                    DatabaseProvider.getDatabase(this@FavoritesActivity)

                                database.favoriteCharacterDao()
                                    .updateFavorite(favorite)

                                database.favoriteCharacterDao()
                                    .getAllFavorites()
                            }

                            adapter.updateList(updatedFavorites)
                        }
                    }
                    .setNegativeButton("Cancelar", null)
                    .show()
            }
        )

        recyclerFavorites.adapter = adapter

        lifecycleScope.launch {

            val favorites = withContext(Dispatchers.IO) {
                val database = DatabaseProvider.getDatabase(this@FavoritesActivity)

                database.favoriteCharacterDao().getAllFavorites()
            }

            adapter.updateList(favorites)
        }
    }
}
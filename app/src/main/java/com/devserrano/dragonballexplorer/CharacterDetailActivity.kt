package com.devserrano.dragonballexplorer

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.devserrano.dragonballexplorer.database.DatabaseProvider
import com.devserrano.dragonballexplorer.database.FavoriteCharacter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class CharacterDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_character_detail)

        val imgCharacterDetail = findViewById<ImageView>(R.id.imgCharacterDetail)
        val tvNameDetail = findViewById<TextView>(R.id.tvNameDetail)
        val tvRaceDetail = findViewById<TextView>(R.id.tvRaceDetail)
        val tvKiDetail = findViewById<TextView>(R.id.tvKiDetail)
        val btnBackDetail = findViewById<Button>(R.id.btnBackDetail)
        val tvMaxKiDetail = findViewById<TextView>(R.id.tvMaxKiDetail)
        val tvGenderDetail = findViewById<TextView>(R.id.tvGenderDetail)
        val tvAffiliationDetail = findViewById<TextView>(R.id.tvAffiliationDetail)
        val tvDescriptionDetail = findViewById<TextView>(R.id.tvDescriptionDetail)
        val btnAddFavorite = findViewById<Button>(R.id.btnAddFavorite)
        val id = intent.getIntExtra("id", 0)

        val name = intent.getStringExtra("name")
        val race = intent.getStringExtra("race")
        val ki = intent.getStringExtra("ki")
        val image = intent.getStringExtra("image")
        val maxKi = intent.getStringExtra("maxKi")
        val gender = intent.getStringExtra("gender")
        val affiliation = intent.getStringExtra("affiliation")
        val description = intent.getStringExtra("description")

        tvNameDetail.text = name
        tvRaceDetail.text = "Raza: $race"
        tvKiDetail.text = "Ki: $ki"
        tvMaxKiDetail.text = "Ki máximo: $maxKi"
        tvGenderDetail.text = "Género: $gender"
        tvAffiliationDetail.text = "Afiliación: $affiliation"
        tvDescriptionDetail.text = description

        Glide.with(this)
            .load(image)
            .into(imgCharacterDetail)

        btnAddFavorite.setOnClickListener {

            val favorite = FavoriteCharacter(
                id,
                name ?: "",
                race ?: "",
                ki ?: "",
                image ?: "",
                ""
            )

            lifecycleScope.launch {

                withContext(Dispatchers.IO) {

                    val database = DatabaseProvider.getDatabase(this@CharacterDetailActivity)

                    database.favoriteCharacterDao().insertFavorite(favorite)

                }

                Toast.makeText(

                    this@CharacterDetailActivity,

                    "Agregado a favoritos",

                    Toast.LENGTH_SHORT

                ).show()

            }
        }

        btnBackDetail.setOnClickListener {
            finish()
        }
    }
}
package com.devserrano.dragonballexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devserrano.dragonballexplorer.adapters.CharacterAdapter
import com.devserrano.dragonballexplorer.models.DragonBallCharacter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCharacters)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val characterList = listOf(

            DragonBallCharacter(

                "Goku",
                "Saiyan",
                "9000",
                ""
            ),
            DragonBallCharacter(
                "Vegeta",
                "Saiyan",
                "8500",
                ""
            ),
            DragonBallCharacter(
                "Freezer",
                "Frieza Race",
                "12000",
                ""
            )
        )
        val adapter = CharacterAdapter(characterList)

        recyclerView.adapter = adapter
    }
}
package com.devserrano.dragonballexplorer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devserrano.dragonballexplorer.adapters.CharacterAdapter
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.devserrano.dragonballexplorer.network.RetrofitInstance
import com.devserrano.dragonballexplorer.models.CharacterResponse
import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import android.content.Intent
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCharacters)
        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnLogout = findViewById<TextView>(R.id.btnLogout)
        val navFavorites = findViewById<TextView>(R.id.navFavorites)

        btnLogout.setOnClickListener {

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(
                this@MainActivity,
                LoginActivity::class.java
            )

            startActivity(intent)
            finish()
        }
        navFavorites.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                FavoritesActivity::class.java
            )

            startActivity(intent)

        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitInstance.api.getCharacters()
            .enqueue(object : Callback<CharacterResponse> {

                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {

                    if (response.isSuccessful) {

                        val characterList = response.body()?.items

                        if (characterList != null) {

                            val adapter = CharacterAdapter(characterList)

                            recyclerView.adapter = adapter

                            etSearch.addTextChangedListener(object : TextWatcher {

                                override fun beforeTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    count: Int,
                                    after: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    s: CharSequence?,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {
                                    val query = s.toString().trim()

                                    val filteredList = characterList.filter { character ->
                                        character.name.contains(query, ignoreCase = true)
                                    }

                                    adapter.updateList(filteredList)
                                }

                                override fun afterTextChanged(s: Editable?) {
                                }
                            })
                        }

                    }

                }

                override fun onFailure(
                    call: Call<CharacterResponse>,
                    t: Throwable
                ) {

                    Log.e("API_ERROR", t.message.toString())
                    Toast.makeText(

                        this@MainActivity,

                        "Error al cargar personajes. Revisa tu conexión.",

                        Toast.LENGTH_LONG

                    ).show()

                }



            })

    }
}
package com.devserrano.dragonballexplorer.network
import com.devserrano.dragonballexplorer.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET



interface ApiService {
    @GET("characters")
    fun getCharacters(): Call<CharacterResponse>

}
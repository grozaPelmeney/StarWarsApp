package com.example.starwarsapp.data.retrofit

import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.db.character.Characters
import com.example.starwarsapp.db.film.Films
import com.example.starwarsapp.db.planet.Planet
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiServices {
    @GET("films")
    suspend fun getFilms() : Films

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") id: Int) : Film

    @GET("people")
    suspend fun getCharacters() : ArrayList<Characters>

    @GET("people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Character

    @GET()
    suspend fun getCharacterByUrl(@Url url: String) : Character

    @GET()
    suspend fun getPlanetByUrl(@Url url: String) : Planet

    @GET("planet/{id}")
    suspend fun getPlanetById(@Path("id") id: Int) : Planet
}
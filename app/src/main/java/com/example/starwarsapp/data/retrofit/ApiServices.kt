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
    suspend fun getCharacters() : Characters

    @GET("people/{id}")
    suspend fun getCharactersById(@Path("id") id: Int) : Character

    @GET()
    suspend fun getCharactersByUrl(@Url url: String) : Character

    @GET("planet/{id}")
    suspend fun getPlanetById(@Path("id") id: Int) : Planet
}
package com.example.starwarsapp.data.retrofit

import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.db.film.Films
import com.example.starwarsapp.db.planet.Planet
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("films")
    suspend fun getFilms() : Films

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") id: Int) : Film

    @GET("people")
    suspend fun getCharacters() : ArrayList<Character>

    @GET("people/{id}")
    suspend fun getCharacterById(@Path("id") id: Int) : Character

    @GET("planet/{id}")
    suspend fun getPlanetById(@Path("id") id: Int) : Planet
}
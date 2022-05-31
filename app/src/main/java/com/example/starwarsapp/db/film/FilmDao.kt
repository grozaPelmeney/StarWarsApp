package com.example.starwarsapp.db.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFilm(film: Film)

    @Query("SELECT * FROM films")
    suspend fun getFilms() : List<Film>

    @Query("SELECT EXISTS(SELECT * FROM films)")
    suspend fun containsFilms() : Boolean
}
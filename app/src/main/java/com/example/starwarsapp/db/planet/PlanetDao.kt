package com.example.starwarsapp.db.planet

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlanetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlanet(planet: Planet)

    @Query("SELECT * FROM planets")
    suspend fun getPlanets() : List<Planet>

    @Query("SELECT * FROM planets WHERE url = :url")
    suspend fun getPlanetByUrl(url: String) : Planet
}
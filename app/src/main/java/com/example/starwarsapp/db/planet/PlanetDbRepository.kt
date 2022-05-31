package com.example.starwarsapp.db.planet

import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.db.film.FilmDao

class PlanetDbRepository(private val planetDao: PlanetDao) {

    suspend fun getPlanets() =
        planetDao.getPlanets()

    suspend fun getPlanetByUrl(url: String) =
        planetDao.getPlanetByUrl(url)

    suspend fun addPlanet(planet: Planet) =
        planetDao.addPlanet(planet)
}
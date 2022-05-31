package com.example.starwarsapp.db.film

class FilmDbRepository(private val filmDao: FilmDao) {

    suspend fun getFilms() =
        filmDao.getFilms()

    suspend fun addFilm(film: Film) =
        filmDao.addFilm(film)

    suspend fun containsFilms() =
        filmDao.containsFilms()
}
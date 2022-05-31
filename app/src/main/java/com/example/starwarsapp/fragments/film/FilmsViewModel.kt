package com.example.starwarsapp.fragments.film

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.db.film.Film
import com.example.starwarsapp.db.film.FilmDbRepository
import com.example.starwarsapp.utils.Result
import kotlinx.coroutines.Dispatchers

class FilmsViewModel(private val dbRepository: FilmDbRepository) : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }

    fun getFilmsFromDb() = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            if (!dbRepository.containsFilms()) {
                throw Exception("")
            }
            emit(Result.success(data = dbRepository.getFilms()))
        } catch (e: Exception) {
            emit(Result.error(data = null, message = "Нет сохраненных данных"))
        }
    }

    fun getFilmsFromApi() = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            val filmsList = apiRepository.getFilms()
            emit(Result.success(data = filmsList.films))
            saveFilmsToDb(filmsList.films)

        } catch (e: Exception) {
            emit(Result.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }

    private suspend fun saveFilmsToDb(films: List<Film>) {
        films.forEach { film ->
            dbRepository.addFilm(film)
        }
        Log.e("AA", "сохранено")
    }
}
package com.example.starwarsapp.fragments.planet

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.db.film.FilmDbRepository
import com.example.starwarsapp.db.planet.PlanetDbRepository
import com.example.starwarsapp.utils.Result
import kotlinx.coroutines.Dispatchers

class PlanetViewModel(private val dbRepository: PlanetDbRepository) : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }

    fun getPlanetFromDb(url: String) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            Log.e("AA", "1")
            val planet = dbRepository.getPlanetByUrl(url)
            if (planet.name == "") throw Exception()
            emit(Result.success(data = planet))
        } catch (e: Exception) {
            emit(Result.error(data = null, message = "Нет сохраненных данных"))
        }
    }

    fun getPlanetFromApi(url: String) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            val planet = apiRepository.getPlanetByUrl(url)
            emit(Result.success(data = planet))
            dbRepository.addPlanet(planet)
        } catch (e: Exception) {
            Log.e("AA", e.toString())
            emit(Result.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }
}
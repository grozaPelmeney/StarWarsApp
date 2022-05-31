package com.example.starwarsapp.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.utils.Result
import kotlinx.coroutines.Dispatchers

class PlanetViewModel : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }

    fun getPlanet(url: String) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(data = apiRepository.getPlanetByUrl(url)))
        } catch (e: Exception) {
            Log.e("AA", e.toString())
            emit(Result.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }
}
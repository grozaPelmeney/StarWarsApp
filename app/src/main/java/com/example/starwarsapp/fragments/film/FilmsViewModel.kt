package com.example.starwarsapp.fragments.film

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.data.retrofit.Common
import com.example.starwarsapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class FilmsViewModel : ViewModel() {
   // private val filmRepository = FilmRepository()
    private val apiRepository by lazy { ApiRepository() }

    fun getFilms() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getFilms()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }
}
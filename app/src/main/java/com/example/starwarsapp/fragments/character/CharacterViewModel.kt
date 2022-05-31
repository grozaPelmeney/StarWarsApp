package com.example.starwarsapp.fragments.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.utils.Result
import kotlinx.coroutines.Dispatchers

class CharacterViewModel : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }

   fun getCharacters(characterUrls: List<String>) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            val charactersList = mutableListOf<Character>()
            characterUrls.forEach { url ->
                charactersList.add(apiRepository.getCharacterByUrl(url))
            }
            emit(Result.success(data = charactersList.toList()))
        } catch (e: Exception) {
            Log.e("AA", e.toString())
            emit(Result.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }
}
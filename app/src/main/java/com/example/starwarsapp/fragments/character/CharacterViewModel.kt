package com.example.starwarsapp.fragments.character

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }
    val characters = MutableLiveData<List<Character>>()


   fun getCharacters(characterUrls: List<String>) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val charactersList = mutableListOf<Character>()
            characterUrls.forEach { url ->
                charactersList.add(apiRepository.getCharactersByUrl(url))
            }
            emit(Resource.success(data = charactersList.toList()))
        } catch (e: Exception) {
            Log.e("AA", e.toString())
            emit(Resource.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }


    fun getCharactersInfo(characterUrls: List<String>) {
        val charactersList = mutableListOf<Character>()
        CoroutineScope(Dispatchers.IO).launch {
            characterUrls.forEach { url ->
                try {
                    charactersList.add(apiRepository.getCharactersByUrl(url))
                } catch (e: Exception) {

                }
            }
        }.invokeOnCompletion {
            characters.postValue(charactersList)
        }
    }
}
package com.example.starwarsapp.fragments.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.starwarsapp.data.retrofit.ApiRepository
import com.example.starwarsapp.db.character.Character
import com.example.starwarsapp.db.character.CharacterDbRepository
import com.example.starwarsapp.utils.Result
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(private val dbRepository: CharacterDbRepository) : ViewModel() {
    private val apiRepository by lazy { ApiRepository() }

    fun getCharactersFromDb(filmUrl: String, characterUrls: List<String>) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            val characters = dbRepository.getCharacters().filter { it.filmsUrls.contains(filmUrl) }
            if (characters.size != characterUrls.size) throw Exception()
            emit(Result.success(data = characters))
        } catch (e: Exception) {
            emit(Result.error(data = null, message = "Нет сохраненных данных"))
        }
    }

   fun getCharactersFromApi(characterUrls: List<String>) = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            val charactersList = mutableListOf<Character>()
            characterUrls.forEach { url ->
                charactersList.add(apiRepository.getCharacterByUrl(url))
            }
            emit(Result.success(data = charactersList.toList()))
            saveCharactersToDb(charactersList)
        } catch (e: Exception) {
            Log.e("AA", e.toString())
            emit(Result.error(data = null, message = "Не удалось загрузить данные!"))
        }
    }

    private suspend fun saveCharactersToDb(characters: List<Character>) {
        characters.forEach { character ->
            dbRepository.addCharacter(character)
        }
    }
}
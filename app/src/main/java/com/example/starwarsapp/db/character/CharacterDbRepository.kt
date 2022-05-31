package com.example.starwarsapp.db.character


class CharacterDbRepository(private val characterDao: CharacterDao) {

    suspend fun getCharacters() =
        characterDao.getCharacters()

    suspend fun addCharacter(character: Character) =
        characterDao.addCharacter(character)

    suspend fun containsCharacters() =
        characterDao.containsCharacters()
}

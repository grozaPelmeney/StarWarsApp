package com.example.starwarsapp.db.character

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(character: Character)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters() : List<Character>

    @Query("SELECT EXISTS(SELECT * FROM characters)")
    suspend fun containsCharacters() : Boolean
}

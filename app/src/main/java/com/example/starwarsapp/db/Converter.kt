package com.example.starwarsapp.db

import androidx.room.TypeConverter
import com.example.starwarsapp.db.character.Character
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun toList(stringListString: String): List<String> {
        return stringListString.split(" ").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = " ")
    }
}
package com.example.starwarsapp.db.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey
    @SerializedName("name")
    @ColumnInfo(name = "character_name")
    val name: String,

    @SerializedName("birth_year")
    val birthYear: String,

    @SerializedName("gender")
    val sex: String,

    @SerializedName("homeworld")
    val planetUrl: String,

    @SerializedName("films")
    @ColumnInfo(name = "character_filmsUrls")
    val filmsUrls: List<String>
)
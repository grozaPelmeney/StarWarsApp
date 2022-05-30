package com.example.starwarsapp.db.character

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("name")
    val name: String,
    @SerializedName("birth_year")
    val birthYear: String,
    @SerializedName("gender")
    val sex: String,
)
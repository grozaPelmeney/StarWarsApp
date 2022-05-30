package com.example.starwarsapp.db.character

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("results")
    val characters: ArrayList<Character>
)
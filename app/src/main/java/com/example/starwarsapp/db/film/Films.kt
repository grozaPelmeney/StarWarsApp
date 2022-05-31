package com.example.starwarsapp.db.film

import com.google.gson.annotations.SerializedName

data class Films(
    @SerializedName("results")
    val films: List<Film>
)
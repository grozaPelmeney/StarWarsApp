package com.example.starwarsapp.db.planet

import com.google.gson.annotations.SerializedName

data class Planet(
    @SerializedName("name")
    val name: String,
    @SerializedName("climate")
    val climate: String,
    @SerializedName("diameter")
    val diameter: String,
    @SerializedName("gravity")
    val gravity: String,
    @SerializedName("population")
    val population: String,
    @SerializedName("terrain")
    val terrain: String,
)
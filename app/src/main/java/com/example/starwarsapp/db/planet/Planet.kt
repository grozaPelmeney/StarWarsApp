package com.example.starwarsapp.db.planet

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "planets")
data class Planet(
    @PrimaryKey
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
    @SerializedName("url")
    val url: String,
)
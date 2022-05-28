package com.example.starwarsapp.db.film

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class Film(
    val title: String,
    @PrimaryKey
    @SerializedName("episode_id")
    val episodeId: Int,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val characters: ArrayList<String>
)
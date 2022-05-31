package com.example.starwarsapp.db.film

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "films")
data class Film(
    @PrimaryKey
    @SerializedName("title")
    val title: String,

    @SerializedName("episode_id")
    val episodeId: Int,

    @SerializedName("director")
    val director: String,

    @SerializedName("producer")
    val producer: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("characters")
    val characters: List<String>,

    @SerializedName("url")
    val url: String
)
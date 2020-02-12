package com.msousa.tmdbredux.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val runtime: String,
    val releaseDate: String,
    val voteAverage: String,
    val voteCount: String,
    val revenue: String,
    val status: String,
    val tagline: String,
    val originalLanguage: String,
    val homepage: String,
    val popularity: String
)
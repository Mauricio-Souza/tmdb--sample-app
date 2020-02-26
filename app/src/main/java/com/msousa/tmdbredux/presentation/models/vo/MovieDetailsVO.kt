package com.msousa.tmdbredux.presentation.models.vo

data class MovieDetailsVO (
    val homepage: String,
    val id: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: String,
    val runtime: String,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: String,
    val voteCount: String,
    val radius: Int = 10
)
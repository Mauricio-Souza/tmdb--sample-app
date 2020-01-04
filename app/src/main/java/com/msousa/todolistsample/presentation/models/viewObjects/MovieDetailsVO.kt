package com.msousa.todolistsample.presentation.models.viewObjects

data class MovieDetailsVO (
    val adult: Boolean,
    val genres: List<GenreVO>,
    val homepage: String,
    val id: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
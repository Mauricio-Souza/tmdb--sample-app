package com.msousa.tmdbredux.redux.middlewares.mappers.models

data class MovieMapper (
    val adult: Boolean,
    val genreIds: List<Int>,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
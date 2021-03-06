package com.msousa.tmdbredux.presentation.models.vo

data class MovieVO (
    val id: String,
    val posterPath: String,
    val title: String,
    val voteAverage: String,
    val radius: Int = 10
)
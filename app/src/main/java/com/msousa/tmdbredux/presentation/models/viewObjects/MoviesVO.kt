package com.msousa.tmdbredux.presentation.models.viewObjects

import com.msousa.tmdbredux.data.remote.responses.Movie

data class MoviesVO (
    val posterPath: String = "",
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val items: List<Movie> = emptyList()
)
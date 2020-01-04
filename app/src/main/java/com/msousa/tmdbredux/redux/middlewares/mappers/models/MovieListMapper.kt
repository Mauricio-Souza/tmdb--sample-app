package com.msousa.tmdbredux.redux.middlewares.mappers.models

import com.msousa.tmdbredux.data.remote.responses.Movie

data class MovieListMapper (
    val posterPath: String,
    val name: String,
    val description: String,
    val id: String,
    val items: List<Movie>
)
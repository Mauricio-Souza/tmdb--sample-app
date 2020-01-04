package com.msousa.todolistsample.redux.middlewares.mappers.models

import com.msousa.todolistsample.data.remote.responses.Movie

data class MovieListMapper (
    val posterPath: String,
    val name: String,
    val description: String,
    val id: String,
    val items: List<Movie>
)
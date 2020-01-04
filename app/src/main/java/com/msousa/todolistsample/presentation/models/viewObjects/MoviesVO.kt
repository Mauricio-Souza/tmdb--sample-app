package com.msousa.todolistsample.presentation.models.viewObjects

import com.msousa.todolistsample.data.remote.responses.Movie

data class MoviesVO (
    val posterPath: String = "",
    val name: String = "",
    val description: String = "",
    val id: String = "",
    val items: List<Movie> = emptyList()
) : BaseVO
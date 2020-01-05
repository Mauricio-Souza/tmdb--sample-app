package com.msousa.tmdbredux.presentation.models.viewObjects

data class MoviesVO (
    val name: String = "",
    val items: List<MovieVO> = emptyList()
)
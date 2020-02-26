package com.msousa.tmdbredux.presentation.models.vo

data class MoviesVO (
    val name: String = "",
    val items: List<MovieVO> = emptyList()
)
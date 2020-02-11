package com.msousa.tmdbredux.data.local.entities

interface IBaseMovieEntity {
    val id: Long
    val title: String
    val posterPath: String
    val voteAverage: String
}

interface IMovieEntity : IBaseMovieEntity  {
    override val id: Long
    override val title: String
    override val posterPath: String
    override val voteAverage: String
    val overview: String
    val runtime: String
    val releaseDate: String
    val voteCount: String
    val revenue: String
    val status: String
    val tagline: String
    val originalLanguage: String
    val homepage: String
    val popularity: String
}
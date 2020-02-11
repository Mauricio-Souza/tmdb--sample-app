package com.msousa.tmdbredux.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = false)
    override val id: Long,
    override val title: String,
    override val overview: String,
    override val posterPath: String,
    override val runtime: String,
    override val releaseDate: String,
    override val voteAverage: String,
    override val voteCount: String,
    override val revenue: String,
    override val status: String,
    override val tagline: String,
    override val originalLanguage: String,
    override val homepage: String,
    override val popularity: String
) : IMovieEntity
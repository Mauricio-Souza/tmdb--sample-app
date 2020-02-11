package com.msousa.tmdbredux.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    override val id: Long,
    override val title: String,
    override val voteAverage: String,
    override val posterPath: String
) : IBaseMovieEntity
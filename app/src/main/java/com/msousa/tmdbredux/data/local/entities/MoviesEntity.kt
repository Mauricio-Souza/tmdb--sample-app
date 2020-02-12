package com.msousa.tmdbredux.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val title: String,
    val voteAverage: String,
    val posterPath: String
)
package com.msousa.tmdbredux.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.msousa.tmdbredux.data.local.entities.converter.DataConverter
import com.msousa.tmdbredux.data.remote.responses.Movie

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val name: String,
    @TypeConverters(DataConverter::class)
    val movies: List<Movie>
)
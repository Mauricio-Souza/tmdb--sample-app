package com.msousa.tmdbredux.data.local

import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

interface ITMDbDatabaseDataSource {
    suspend fun insertMovies(movies: MoviesEntity)
    suspend fun selectAllMovies() : MoviesEntity
    suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity)
    suspend fun selectMovieDetails(movieId: Long) : MovieDetailsEntity

}
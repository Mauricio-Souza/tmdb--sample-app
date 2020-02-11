package com.msousa.tmdbredux.data.local

import com.msousa.tmdbredux.data.local.entities.IBaseMovieEntity
import com.msousa.tmdbredux.data.local.entities.IMovieEntity
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity

interface ITMDbDatabaseDataSource {
    fun insertMovies(vararg movies: MoviesEntity)
    fun selectAllMovies() : List<IBaseMovieEntity>
    fun insertMovieDetails(movieDetails: MovieDetailsEntity)
    fun selectMovieDetails(movieId: Long) : IMovieEntity

}
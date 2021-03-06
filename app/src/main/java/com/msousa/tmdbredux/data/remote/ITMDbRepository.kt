package com.msousa.tmdbredux.data.remote

import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieDetailsMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieListMapper
import kotlinx.coroutines.flow.Flow

interface ITMDbRepository {
    suspend fun getMovies() : Flow<MovieListMapper>
    suspend fun getMovieDetails(movieId: String) : Flow<MovieDetailsMapper>
}
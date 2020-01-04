package com.msousa.tmdbredux.data.remote

import com.msousa.tmdbredux.redux.middlewares.mappers.models.GuestSessionMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieDetailsMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieListMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.UserTokenMapper
import kotlinx.coroutines.flow.Flow

interface ITMDbRepository {
    suspend fun getGuestSession() : Flow<GuestSessionMapper>
    suspend fun requestToken() : Flow<UserTokenMapper>
    suspend fun getMovies() : Flow<MovieListMapper>
    suspend fun getMovieDetails(movieId: String) : Flow<MovieDetailsMapper>
}
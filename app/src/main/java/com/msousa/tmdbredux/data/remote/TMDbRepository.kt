package com.msousa.tmdbredux.data.remote

import com.msousa.tmdbredux.data.HttpResultHandler
import com.msousa.tmdbredux.redux.middlewares.mappers.toMapper
import kotlinx.coroutines.flow.flow

class TMDbRepository(private val api: TMDbApi = RetrofitProvider.get()) : ITMDbRepository {

    override suspend fun getMovies() = flow {
        val response = api.getMovies().await()
        val movies = HttpResultHandler.handle(response)
        emit(movies.toMapper())
    }

    override suspend fun getMovieDetails(movieId: String) = flow {
        val response = api.getMovieDetails(movieId = movieId).await()
        val movieDetails = HttpResultHandler.handle(response)
        emit(movieDetails.toMapper())
    }
}
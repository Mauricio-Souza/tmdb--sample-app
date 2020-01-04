package com.msousa.todolistsample.data.remote

import com.msousa.todolistsample.data.HttpResultHandler
import com.msousa.todolistsample.redux.middlewares.mappers.toMapper
import kotlinx.coroutines.flow.flow

class TMDbRepository(private val api: TMDbApi = RetrofitProvider.get()) : ITMDbRepository {

    override suspend fun getGuestSession() = flow {
        val response = api.getGuestSession().await()
        val userSession = HttpResultHandler.handle(response)
        emit(userSession.toMapper())
    }

    override suspend fun requestToken() = flow {
        val response = api.requestToken().await()
        val token = HttpResultHandler.handle(response)
        emit(token.toMapper())
    }

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
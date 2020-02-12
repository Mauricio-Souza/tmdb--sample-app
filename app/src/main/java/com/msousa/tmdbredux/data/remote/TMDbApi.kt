package com.msousa.tmdbredux.data.remote

import com.msousa.tmdbredux.data.remote.responses.MovieDetailsResponse
import com.msousa.tmdbredux.data.remote.responses.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDbApi {

    @GET(value = "list/1")
    fun getMovies() : Deferred<Response<MovieListResponse>>

    @GET(value = "movieEntity/{movie_id}")
    fun getMovieDetails(@Path(value = "movie_id") movieId: String) : Deferred<Response<MovieDetailsResponse>>

}
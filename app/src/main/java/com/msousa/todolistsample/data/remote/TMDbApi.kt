package com.msousa.todolistsample.data.remote

import com.msousa.todolistsample.data.remote.responses.GuestSessionResponse
import com.msousa.todolistsample.data.remote.responses.MovieDetailsResponse
import com.msousa.todolistsample.data.remote.responses.MovieListResponse
import com.msousa.todolistsample.data.remote.responses.UserTokenResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TMDbApi {

    @GET(value = "authentication/guest_session/new")
    fun getGuestSession() : Deferred<Response<GuestSessionResponse>>

    @GET(value = "authentication/token/new")
    fun requestToken() : Deferred<Response<UserTokenResponse>>

    @GET(value = "list/1")
    fun getMovies() : Deferred<Response<MovieListResponse>>

    @GET(value = "movie/{movie_id}")
    fun getMovieDetails(@Path(value = "movie_id") movieId: String) : Deferred<Response<MovieDetailsResponse>>

}
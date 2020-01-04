package com.msousa.todolistsample.data.remote.responses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName(value = "poster_path")
    val posterPath: String,
    val name: String,
    val description: String,
    val id: String,
    val items: List<Movie>
)

data class Movie(
    @SerializedName(value = "adult")
    val adult: Boolean,
    @SerializedName(value = "genre_ids")
    val genreIds: List<Int>,
    @SerializedName(value = "id")
    val id: Int,
    @SerializedName(value = "overview")
    val overview: String,
    @SerializedName(value = "popularity")
    val popularity: Double,
    @SerializedName(value = "poster_path")
    val posterPath: String,
    @SerializedName(value = "release_date")
    val releaseDate: String,
    @SerializedName(value = "title")
    val title: String,
    @SerializedName(value = "vote_average")
    val voteAverage: Double,
    @SerializedName(value = "vote_count")
    val voteCount: Int
)
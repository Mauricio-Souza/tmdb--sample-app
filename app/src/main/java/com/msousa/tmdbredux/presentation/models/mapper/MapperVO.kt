package com.msousa.tmdbredux.presentation.models.mapper

import com.msousa.tmdbredux.BuildConfig
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity
import com.msousa.tmdbredux.data.remote.exceptions.TMDbException
import com.msousa.tmdbredux.data.remote.responses.Movie
import com.msousa.tmdbredux.extensions.formatDate
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieListMapper
import com.msousa.tmdbredux.presentation.models.viewObjects.*
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieDetailsMapper

fun MovieListMapper.toVO() = MoviesVO(
    name = name,
    items = items.toVO()
)

fun List<Movie>.toVO() = map {
    MovieVO (
        id = it.id.toString(),
        posterPath = "${BuildConfig.BASE_IMAGE_URL}${it.posterPath}",
        title = it.title,
        voteAverage = it.voteAverage.toString()
    )
}

fun MovieDetailsMapper.toVO() = MovieDetailsVO(
    homepage = homepage,
    id = id.toString(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = "${BuildConfig.BASE_IMAGE_URL}${posterPath}",
    releaseDate = releaseDate.formatDate(),
    revenue = revenue,
    runtime = "$runtime min",
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage.toString(),
    voteCount = voteCount
)

fun List<MoviesEntity>.map() = map {
    MovieVO (
        id = it.id.toString(),
        posterPath = "${BuildConfig.BASE_IMAGE_URL}${it.posterPath}",
        title = it.title,
        voteAverage = it.voteAverage
    )
}

fun MovieDetailsEntity.toVO() = MovieDetailsVO(
    homepage = homepage,
    id = id.toString(),
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity.toDouble(),
    posterPath = "${BuildConfig.BASE_IMAGE_URL}${posterPath}",
    releaseDate = releaseDate.formatDate(),
    revenue = revenue.toInt(),
    runtime = "$runtime min",
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount.toInt()
)

fun TMDbException.toVO() = ErrorMessageVO(
    message = userMessage
)
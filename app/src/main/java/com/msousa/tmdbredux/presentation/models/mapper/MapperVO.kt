package com.msousa.tmdbredux.presentation.models.mapper

import com.msousa.tmdbredux.BuildConfig
import com.msousa.tmdbredux.data.remote.exceptions.TMDbException
import com.msousa.tmdbredux.data.remote.responses.Movie
import com.msousa.tmdbredux.redux.middlewares.mappers.models.GenreMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieDetailsMapper
import com.msousa.tmdbredux.redux.middlewares.mappers.models.MovieListMapper
import com.msousa.tmdbredux.presentation.models.viewObjects.*

fun MovieListMapper.toVO() = MoviesVO(
    name = name,
    items = items.map()
)

fun List<GenreMapper>.mapToVO() = map {
    GenreVO(
        id = it.id.toString(),
        name = it.name
    )
}

fun List<Movie>.map() = map {
    MovieVO (
        id = it.id.toString(),
        posterPath = "${BuildConfig.BASE_IMAGE_URL}${it.posterPath}",
        title = it.title,
        voteAverage = it.voteAverage.toString()
    )
}

fun MovieDetailsMapper.toVO() = MovieDetailsVO(
    adult = adult,
    genres = genres.mapToVO(),
    homepage = homepage,
    id = id.toString(),
    imdbId = imdbId,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    voteAverage = voteAverage,
    voteCount = voteCount
)

fun TMDbException.map() = ErrorMessageVO(
    message = userMessage
)
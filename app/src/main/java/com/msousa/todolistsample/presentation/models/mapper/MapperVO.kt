package com.msousa.todolistsample.presentation.models.mapper

import com.msousa.todolistsample.data.remote.exceptions.TMDbException
import com.msousa.todolistsample.redux.middlewares.mappers.models.GenreMapper
import com.msousa.todolistsample.redux.middlewares.mappers.models.MovieDetailsMapper
import com.msousa.todolistsample.redux.middlewares.mappers.models.MovieListMapper
import com.msousa.todolistsample.presentation.models.viewObjects.*

fun MovieListMapper.toVO() = MoviesVO(
    posterPath = posterPath,
    name = name,
    description = description,
    id = id,
    items = items
)

fun List<GenreMapper>.mapToVO() = map {
    GenreVO(
        id = it.id.toString(),
        name = it.name
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
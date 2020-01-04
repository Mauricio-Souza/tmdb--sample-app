package com.msousa.todolistsample.redux.middlewares.mappers

import com.msousa.todolistsample.data.remote.responses.*
import com.msousa.todolistsample.redux.middlewares.mappers.models.*

fun GuestSessionResponse.toMapper() = GuestSessionMapper(
    expiresAt = expiresAt,
    guestSessionId = guestSessionId,
    success = success
)

fun UserTokenResponse.toMapper() = UserTokenMapper(
    expiresAt = expiresAt,
    requestToken = requestToken,
    success = success
)

fun MovieListResponse.toMapper() = MovieListMapper(
    posterPath = posterPath,
    name = name,
    description = description,
    id = id,
    items = items
)

fun Genre.toMapper() = GenreMapper(
    id = id,
    name = name
)

fun List<Genre>.map() = map {
        GenreMapper(
            id = it.id,
            name = it.name
        )
    }

fun MovieDetailsResponse.toMapper() = MovieDetailsMapper(
    adult = adult,
    genres = genres.map(),
    homepage = homepage,
    id = id,
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
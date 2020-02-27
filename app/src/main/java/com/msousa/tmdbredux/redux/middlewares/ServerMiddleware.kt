package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoInternetException
import com.msousa.tmdbredux.presentation.models.mapper.*
import com.msousa.tmdbredux.redux.actions.*

class ServerMiddleware(private val repository: ITMDbRepository) : IMiddleware {

    override suspend fun onNext(action: Action): Action {
        var newAction = action
        when (action) {
            is ViewAction.FetchMovieList -> {
                runHttpCall(
                    onExecute = { repository.getMovies() },
                    onSuccess = { data -> newAction = FromDatabase.InsertMovies(data.toEntity()) },
                    onFailure = { error ->
                        newAction = when (error) {
                            is TMDbNoInternetException -> FromDatabase.SelectAllMovies
                            else -> Result.Failure(error)

                        }
                    }
                )
            }
            is ViewAction.FetchMovieDetails -> {
                runHttpCall(
                    onExecute = { repository.getMovieDetails(action.movieId) },
                    onSuccess = { data -> newAction = FromDatabase.InsertMovieDetails(data.toEntity()) },
                    onFailure = { error ->
                        newAction = when (error) {
                            is TMDbNoInternetException -> {
                                FromDatabase.SelectMovieDetails(action.movieId.toLong())
                            }
                            else -> Result.Failure(error)
                        }
                    }
                )
            }
        }
        return newAction
    }

}
package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoInternetException
import com.msousa.tmdbredux.presentation.models.mapper.*
import com.msousa.tmdbredux.redux.actions.*

class ServerMiddleware(private val repository: ITMDbRepository) : INext {

    override suspend fun onNext(action: Action): Action {
        var newAction = action
        when (action) {
            is ViewAction.OnMainActivityCreated -> {
                runHttpCall(
                    onExecute = { repository.getMovies() },
                    onSuccess = { data -> newAction = Result.Success(data.toVO()) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO()) }
                )
            }
            is ViewAction.OnMovieDetailsActivityCreated -> {
                runHttpCall(
                    onExecute = { repository.getMovieDetails(action.movieId) },
                    onSuccess = { data -> newAction = Result.Success(data.toVO()) },
                    onFailure = { error ->
                        newAction = when (error) {
                            is TMDbNoInternetException -> {
                                FromDatabase.SelectMovieDetails(action.movieId.toLong())
                            }
                            else -> Result.Failure(error.toVO())
                        }
                    }
                )
            }
        }
        return newAction
    }

}
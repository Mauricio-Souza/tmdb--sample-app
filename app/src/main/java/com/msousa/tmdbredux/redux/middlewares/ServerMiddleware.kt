package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.runHttpCall
import com.msousa.tmdbredux.presentation.models.mapper.*
import com.msousa.tmdbredux.redux.actions.*

class ServerMiddleware(private val repository: ITMDbRepository) : INext {

    override suspend fun onNext(action: Action): Action {
        var newAction = action
        when (action) {
            is ViewAction.OnMainActivityCreated -> {
                runHttpCall(
                    onExecute = { repository.getMovies() },
                    onSuccess = { data -> newAction = ServerResponse.Success(data.toVO()) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            is ViewAction.OnMovieDetailsActivityCreated -> {
                runHttpCall(
                    onExecute = { repository.getMovieDetails(action.movieId) },
                    onSuccess = { data -> newAction = ServerResponse.Success(data.toVO()) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            is NoInternetConnection -> {
                newAction = Database.Entity<Any>(operation = Database.Operation.DELETE)
            }
        }
        return newAction
    }

}
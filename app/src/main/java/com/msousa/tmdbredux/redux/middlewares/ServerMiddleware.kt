package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.ViewAction
import com.msousa.tmdbredux.redux.actions.ServerResponse
import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.runHttpCall
import com.msousa.tmdbredux.presentation.models.mapper.*

class ServerMiddleware(private val repository: ITMDbRepository) : INext {

    override suspend fun onNext(action: Action, next: INext): Action {
        var newAction = action
        when (action) {
            is ViewAction.OnLoginButtonClicked -> {
                runHttpCall(
                    onExecute = { repository.getGuestSession() },
                    onSuccess = { data -> newAction = ServerResponse.Success(data) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            is ViewAction.OnMainActivityCreated -> {
                runHttpCall(
                    onExecute = { repository.getMovies() },
                    onSuccess = { data -> newAction = ServerResponse.Success(data.toVO()) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            is ViewAction.OnMovieClicked -> {
                runHttpCall(
                    onExecute = { repository.getMovieDetails(action.movieId)},
                    onSuccess = { data -> newAction = ServerResponse.Success(data.toVO()) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            else -> {
                newAction = next.onNext(action, next)
            }
        }
        return newAction
    }

}
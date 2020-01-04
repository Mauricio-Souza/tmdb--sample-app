package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.NavigationAction
import com.msousa.tmdbredux.redux.actions.ServerRequest
import com.msousa.tmdbredux.redux.actions.ServerResponse
import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.runHttpCall
import com.msousa.tmdbredux.presentation.models.mapper.*

class ServerMiddleware(private val repository: ITMDbRepository) : INext {

    override suspend fun onNext(action: Action, next: INext): Action {
        var newAction = action
        when (action) {
            is ServerRequest.Authenticate -> {
                runHttpCall(
                    onExecute = { repository.getGuestSession() },
                    onSuccess = { newAction = ServerResponse.Success(NavigationAction.ToHome) },
                    onFailure = { error -> newAction = ServerResponse.Failure(error.map()) }
                )
            }
            is ServerRequest.GetMovies -> {
                newAction = NavigationAction.ToHome
            }
            is ServerRequest.GetMovieDetails -> {
                newAction = NavigationAction.ToHome
            }
            else -> {
                newAction = next.onNext(action, next)
            }
        }
        return newAction
    }

}
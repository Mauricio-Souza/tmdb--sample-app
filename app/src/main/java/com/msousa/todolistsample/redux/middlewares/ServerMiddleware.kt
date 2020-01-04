package com.msousa.todolistsample.redux.middlewares

import com.msousa.todolistsample.redux.actions.Action
import com.msousa.todolistsample.redux.actions.NavigationAction
import com.msousa.todolistsample.redux.actions.ServerRequest
import com.msousa.todolistsample.redux.actions.ServerResponse
import com.msousa.todolistsample.data.remote.ITMDbRepository
import com.msousa.todolistsample.data.remote.runHttpCall
import com.msousa.todolistsample.presentation.models.mapper.map

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
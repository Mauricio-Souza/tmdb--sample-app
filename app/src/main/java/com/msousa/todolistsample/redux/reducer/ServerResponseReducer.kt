package com.msousa.todolistsample.redux.reducer

import com.msousa.todolistsample.redux.actions.Action
import com.msousa.todolistsample.redux.actions.ServerResponse
import com.msousa.todolistsample.redux.state.State
import kotlinx.coroutines.flow.flow

object ServerResponseReducer : IReducer {

    override suspend fun apply(state: State, action: Action) = flow {
        var newState = state
        when (action) {
            is ServerResponse.Success<*> -> {
                newState = state.copy(data = action.data)
            }
            is ServerResponse.Failure -> {
                newState = state.copy(data = action.error)
            }
        }
        emit(newState)
    }
}
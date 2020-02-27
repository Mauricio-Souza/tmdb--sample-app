package com.msousa.tmdbredux.redux.reducer

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.Result
import com.msousa.tmdbredux.redux.state.State
import kotlinx.coroutines.flow.flow

object ResultReducer : IReducer {

    override suspend fun apply(state: State, action: Action) = flow {
        var newState = state
        when (action) {
            is Result.Success<*> -> {
                newState = state.copy(data = action.data)
            }
            is Result.Failure -> {
                newState = state.copy(data = action.error)
            }
        }
        emit(newState)
    }
}
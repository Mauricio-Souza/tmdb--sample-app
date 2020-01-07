package com.msousa.tmdbredux.redux.reducer

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.ViewAction
import com.msousa.tmdbredux.redux.state.State
import kotlinx.coroutines.flow.flow

object NavigationReducer : IReducer {
    override suspend fun apply(state: State, action: Action) = flow {
        var newState = state
        when (action) {
            is ViewAction.OnListItemClicked -> {
                newState = state.copy(data = action.invoke())

            }
            else -> { }
        }
        emit(newState)
    }
}
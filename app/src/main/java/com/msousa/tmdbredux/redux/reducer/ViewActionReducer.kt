package com.msousa.tmdbredux.redux.reducer

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.ViewAction.OnNoInternetConnection
import com.msousa.tmdbredux.redux.actions.ViewAction.OnNoSuchDataFound
import com.msousa.tmdbredux.redux.actions.ViewAction.OnListItemClicked
import com.msousa.tmdbredux.redux.state.State
import kotlinx.coroutines.flow.flow

object ViewActionReducer : IReducer {
    override suspend fun apply(state: State, action: Action) = flow {
        var newState = state
        when (action) {
            is OnListItemClicked -> {
                newState = state.copy(data = action.invoke())

            }
            is OnNoSuchDataFound -> {
                newState = state.copy(data = action.invoke())
            }
            is OnNoInternetConnection -> {
                newState = state.copy(data = action.invoke())
            }
        }
        emit(newState)
    }
}
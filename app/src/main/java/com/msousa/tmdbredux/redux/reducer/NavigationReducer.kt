package com.msousa.tmdbredux.redux.reducer

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.NavigateAction.ShowNoInternetConnectionScreen
import com.msousa.tmdbredux.redux.actions.NavigateAction.ShowNoSuchDataFoundScreen
import com.msousa.tmdbredux.redux.actions.NavigateAction.OpenMovieDetailsScreen
import com.msousa.tmdbredux.redux.state.State
import kotlinx.coroutines.flow.flow

object NavigationReducer : IReducer {
    override suspend fun apply(state: State, action: Action) = flow {
        var newState = state
        when (action) {
            is OpenMovieDetailsScreen -> {
                newState = state.copy(data = action.invoke())

            }
            is ShowNoSuchDataFoundScreen -> {
                newState = state.copy(data = action.invoke())
            }
            is ShowNoInternetConnectionScreen -> {
                newState = state.copy(data = action.invoke())
            }
        }
        emit(newState)
    }
}
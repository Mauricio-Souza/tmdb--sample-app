package com.msousa.tmdbredux.redux.reducer

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.state.State
import kotlinx.coroutines.flow.Flow

interface IReducer {
    suspend fun apply(state: State, action: Action) : Flow<State>
}
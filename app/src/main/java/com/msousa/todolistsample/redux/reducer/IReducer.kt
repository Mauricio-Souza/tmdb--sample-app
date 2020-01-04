package com.msousa.todolistsample.redux.reducer

import com.msousa.todolistsample.redux.actions.Action
import com.msousa.todolistsample.redux.state.State
import kotlinx.coroutines.flow.Flow

interface IReducer {
    suspend fun apply(state: State, action: Action) : Flow<State>
}
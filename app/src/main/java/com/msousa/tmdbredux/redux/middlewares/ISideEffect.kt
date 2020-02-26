package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import kotlinx.coroutines.flow.Flow

interface ISideEffect {
    suspend fun apply(action: Action) : Flow<Action>
}
package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import kotlinx.coroutines.flow.Flow

interface IMiddleware {
    suspend fun apply(action: Action) : Flow<Action>
}
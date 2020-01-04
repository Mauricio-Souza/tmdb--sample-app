package com.msousa.todolistsample.redux.middlewares

import com.msousa.todolistsample.redux.actions.Action
import kotlinx.coroutines.flow.Flow

interface IMiddleware {
    suspend fun apply(action: Action) : Flow<Action>
}
package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action

interface IMiddleware {
    suspend fun onNext(action: Action): Action
}
package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action

class EndOfChain : IMiddleware {
    override suspend fun onNext(action: Action) = action
}
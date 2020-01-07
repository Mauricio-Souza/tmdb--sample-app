package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action

class EndOfChain : INext {
    override suspend fun onNext(action: Action) = action
}
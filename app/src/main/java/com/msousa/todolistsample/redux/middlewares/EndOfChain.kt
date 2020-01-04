package com.msousa.todolistsample.redux.middlewares

import com.msousa.todolistsample.redux.actions.Action

class EndOfChain : INext {
    override suspend fun onNext(action: Action, next: INext) = action
}
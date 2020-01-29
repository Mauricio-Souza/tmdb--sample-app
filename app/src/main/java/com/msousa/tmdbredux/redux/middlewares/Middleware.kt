package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import kotlinx.coroutines.flow.flow

class Middleware(
    private val chain: List<INext>
) : IMiddleware {

    override suspend fun apply(action: Action) = flow {
        var newAction = action
        chain.forEach {
            newAction = it.onNext(newAction)
        }
        emit(newAction)
    }
}
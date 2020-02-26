package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.Result
import kotlinx.coroutines.flow.flow

class SideEffect(
    private val chain: List<IMiddleware>
) : ISideEffect {

    override suspend fun apply(action: Action) = flow {
        var newAction = action
        emit(Result.Loading)
        chain.forEach {
            newAction = it.onNext(newAction)
        }
        emit(newAction)
    }
}
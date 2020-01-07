package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.extensions.noInternetConnection
import android.net.ConnectivityManager
import com.msousa.tmdbredux.redux.store.IStore
import com.msousa.tmdbredux.redux.store.Store
import kotlinx.coroutines.flow.flow

class Middleware(
    private val middlewareList: List<INext>
) : IMiddleware {

    override suspend fun apply(action: Action) = flow {
       var newAction = action
        middlewareList.forEach { newAction = it.onNext(newAction) }
        emit(newAction)
    }
}
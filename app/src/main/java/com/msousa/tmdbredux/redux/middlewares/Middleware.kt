package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.extensions.noInternetConnection
import android.net.ConnectivityManager
import kotlinx.coroutines.flow.flow

class Middleware(
    private val connectivityManager: ConnectivityManager,
    private val middlewareList: List<INext>
) : IMiddleware {

    override suspend fun apply(action: Action) = flow {
       var newAction = action
        if (connectivityManager.noInternetConnection()) {

        }
        middlewareList.forEachIndexed { index, iNext ->
            newAction = middlewareList[index].onNext(newAction, iNext)
        }
        emit(newAction)
    }
}
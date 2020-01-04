package com.msousa.todolistsample.redux.middlewares

import android.net.ConnectivityManager
import com.msousa.todolistsample.extensions.noInternetConnection
import com.msousa.todolistsample.redux.actions.Action
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
package com.msousa.tmdbredux.redux.middlewares

import android.util.Log
import com.msousa.tmdbredux.data.remote.exceptions.TMDbException
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoInternetException
import com.msousa.tmdbredux.data.remote.exceptions.TMDbNoSuchDataFound
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.net.UnknownHostException

suspend inline fun <T> runHttpCall(
    onExecute: () -> Flow<T>,
    crossinline onSuccess: (T) -> Unit,
    onFailure: (TMDbException) -> Unit
) = try {
    onExecute().collect { value ->
        onSuccess(value)
    }
} catch (e: Exception) {
    when (e) {
        is TMDbException -> onFailure(e)
        is UnknownHostException -> onFailure(TMDbNoInternetException())
        else -> Timber.e(e)
    }
}

inline fun <T> runDatabaseOperation(
    onExecute: () -> T,
    onSuccess: (T) -> Unit,
    onFailure: (TMDbException) -> Unit
) = try {
    onExecute().run { onSuccess(this) }
} catch (e: Exception) {
    onFailure(TMDbNoSuchDataFound())
}
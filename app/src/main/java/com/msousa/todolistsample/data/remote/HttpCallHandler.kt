package com.msousa.todolistsample.data.remote

import com.msousa.todolistsample.data.remote.exceptions.TMDbException
import com.msousa.todolistsample.data.remote.exceptions.TMDbNoInternetException
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
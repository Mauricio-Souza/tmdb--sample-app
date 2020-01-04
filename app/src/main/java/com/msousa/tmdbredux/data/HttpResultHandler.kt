package com.msousa.tmdbredux.data

import com.msousa.tmdbredux.data.remote.exceptions.*
import retrofit2.Response

object HttpResultHandler {

    fun <T> handle(response: Response<T>) : T {
        return when (response.code()) {
            200 -> response.body()!!
            204 -> throw TMDbNoContentException()
            401 -> throw TMDbUnauthorizedException()
            404 -> throw TMDbBadRequestException()
            500 -> throw TMDbServerUnavailableException()
            else -> throw TMDbUnknownErrorException()
        }
    }
}


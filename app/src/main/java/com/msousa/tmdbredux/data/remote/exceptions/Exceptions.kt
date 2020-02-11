package com.msousa.tmdbredux.data.remote.exceptions

import com.msousa.tmdbredux.StringResource

abstract class TMDbException : Exception() {
    abstract val userMessage: Int
}

class TMDbBadRequestException(override val userMessage: Int = StringResource.BAD_REQUEST_SERVER_ERROR) : TMDbException()

class TMDbUnauthorizedException(override val userMessage: Int = StringResource.UNAUTHORIZED_SERVER_ERROR) : TMDbException()

class TMDbServerUnavailableException(override val userMessage: Int = StringResource.SERVER_TEMPORARILY_UNAVAILABLE_ERROR) : TMDbException()

class TMDbNoContentException(override val userMessage: Int = StringResource.NO_CONTENT_SERVER_ERROR) : TMDbException()

class TMDbUnknownErrorException(override val userMessage: Int = StringResource.UNKNOWN_SERVER_ERROR) : TMDbException()

class TMDbNoInternetException(override val userMessage: Int = StringResource.NO_INTERNET_CONNECTION_ERROR) : TMDbException()

class TMDbNoSuchDataFound(override val userMessage: Int = StringResource.NO_SUCH_DATA_FOUND_ERROR) : TMDbException()
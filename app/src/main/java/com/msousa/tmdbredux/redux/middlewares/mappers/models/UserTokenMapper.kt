package com.msousa.tmdbredux.redux.middlewares.mappers.models

data class UserTokenMapper (
    val expiresAt: String,
    val requestToken: String,
    val success: Boolean
)
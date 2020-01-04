package com.msousa.tmdbredux.redux.middlewares.mappers.models

data class GuestSessionMapper (
    val expiresAt: String,
    val guestSessionId: String,
    val success: Boolean
)
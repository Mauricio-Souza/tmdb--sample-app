package com.msousa.todolistsample.data.remote.responses
import com.google.gson.annotations.SerializedName


data class UserTokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)
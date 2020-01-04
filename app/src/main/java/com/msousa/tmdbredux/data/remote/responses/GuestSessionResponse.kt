package com.msousa.tmdbredux.data.remote.responses
import com.google.gson.annotations.SerializedName


data class GuestSessionResponse(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("guest_session_id")
    val guestSessionId: String,
    @SerializedName("success")
    val success: Boolean
) : BaseResponse()
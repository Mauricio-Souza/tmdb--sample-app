package com.msousa.todolistsample.data.remote.responses

import com.google.gson.annotations.SerializedName

abstract class BaseResponse(
    @SerializedName(value = "status_message")
    val message: String = "",
    @SerializedName(value = "status_code")
    val code: Int? = 0
)
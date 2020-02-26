package com.msousa.tmdbredux.presentation.models

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorScreen(
    @StringRes val title: Int,
    val icon: Int
) : Parcelable

class ErrorScreenBuilder {
    var title = 0
    var icon = 0

    fun build() = ErrorScreen(
        title = this.title,
        icon = this.icon
    )
}

fun errorScreenBuilder(initializer: ErrorScreenBuilder.() -> Unit): ErrorScreen =
    ErrorScreenBuilder().apply(initializer).build()
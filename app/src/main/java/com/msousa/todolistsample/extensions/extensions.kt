package com.msousa.todolistsample.extensions

import android.net.ConnectivityManager

fun ConnectivityManager.noInternetConnection() = activeNetworkInfo?.isConnectedOrConnecting != true
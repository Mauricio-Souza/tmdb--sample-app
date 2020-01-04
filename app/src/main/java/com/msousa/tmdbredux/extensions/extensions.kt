package com.msousa.tmdbredux.extensions

import android.net.ConnectivityManager

fun ConnectivityManager.noInternetConnection() = activeNetworkInfo?.isConnectedOrConnecting != true
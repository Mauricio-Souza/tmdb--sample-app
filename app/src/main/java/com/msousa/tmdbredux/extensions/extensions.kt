package com.msousa.tmdbredux.extensions

import android.net.ConnectivityManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msousa.tmdbredux.ImageResource

fun ConnectivityManager.noInternetConnection() = activeNetworkInfo?.isConnectedOrConnecting != true

fun ImageView.loadImageUrlWithCornerRadius(url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(radius))
        .fallback(ImageResource.avengers_poster)
        .error(ImageResource.avengers_poster)
        .into(this)
}
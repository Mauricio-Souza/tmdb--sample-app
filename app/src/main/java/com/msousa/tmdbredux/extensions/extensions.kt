package com.msousa.tmdbredux.extensions

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.msousa.tmdbredux.ImageResource
import com.msousa.tmdbredux.StringResource
import java.text.SimpleDateFormat
import java.util.*

fun ConnectivityManager.noInternetConnection() = activeNetworkInfo?.isConnectedOrConnecting != true

fun RecyclerView.ViewHolder.getString(resId: Int) : String {
    check(resId != 0) { StringResource.INVALID_RESOURCE_ID }
    return itemView.context.getString(resId)
}

fun ImageView.loadImageUrlWithCornerRadius(url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(radius))
        .error(ImageResource.avengers_poster)
        .into(this)
}

@SuppressLint("SimpleDateFormat")
fun String.formatDate() : String {
    val from = SimpleDateFormat("yyyy-MM-dd")
    val to = SimpleDateFormat("dd MMM yyyy")
    return to.format(from.parse(this))
}
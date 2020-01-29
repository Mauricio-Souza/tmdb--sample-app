package com.msousa.tmdbredux.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.TypedValue
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msousa.tmdbredux.DrawableResource
import com.msousa.tmdbredux.StringResource
import java.text.SimpleDateFormat

fun ConnectivityManager.noInternetConnection() = activeNetworkInfo?.isConnectedOrConnecting != true

fun RecyclerView.ViewHolder.getString(resId: Int) : String {
    check(resId != 0) { StringResource.INVALID_RESOURCE_ID }
    return itemView.context.getString(resId)
}

fun ImageView.loadImageUrlWithCornerRadius(url: String, radius: Int) {
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(radius))
        .placeholder(CircularProgressDrawable(context).apply {
            setStyle(CircularProgressDrawable.DEFAULT)
            start()
        })
        .error(DrawableResource.avengers_poster)
        .into(this)
}

fun Context.getColorCompat(resId: Int) = ContextCompat.getColor(this, resId)

fun Context.getDimens(dimenId: Int) = resources.getDimension(dimenId)

fun Context.getResourceValue(resId: Int) : Float {
    val outValue = TypedValue()
    resources.getValue(resId, outValue, true)
    return outValue.float
}

@SuppressLint("SimpleDateFormat")
fun String.formatDate() : String {
    val from = SimpleDateFormat("yyyy-MM-dd")
    val to = SimpleDateFormat("dd MMM yyyy")
    return to.format(from.parse(this))
}
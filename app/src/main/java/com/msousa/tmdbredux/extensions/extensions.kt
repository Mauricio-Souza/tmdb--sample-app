package com.msousa.tmdbredux.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.msousa.tmdbredux.DrawableResource
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.loadImageUrl(url: String, radius: Int) {
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

fun Double.formatToMoney() = NumberFormat
    .getCurrencyInstance(Locale.getDefault())
    .format(this)
    .replace("$", "$ ")
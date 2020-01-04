package com.msousa.tmdbredux.presentation

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.showShortToast(resId: Int?) {
    resId?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}
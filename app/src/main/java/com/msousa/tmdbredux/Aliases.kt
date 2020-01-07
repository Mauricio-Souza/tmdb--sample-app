package com.msousa.tmdbredux

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msousa.tmdbredux.redux.state.State

typealias NavigationRouter = () -> Intent

typealias StringResource = R.string

typealias ImageResource = R.drawable

typealias LayoutResource = R.layout

typealias StateLiveData = LiveData<State>

typealias MutableStateLiveData = MutableLiveData<State>
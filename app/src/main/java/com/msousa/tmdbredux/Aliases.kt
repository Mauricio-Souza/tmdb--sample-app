package com.msousa.tmdbredux

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msousa.tmdbredux.redux.state.State

typealias NavigationRouter<T> = () -> T

typealias StringResource = R.string

typealias ResourceId = R.id

typealias DrawableResource = R.drawable

typealias LayoutResource = R.layout

typealias ColorResource = R.color

typealias DimenResource = R.dimen

typealias StateLiveData = LiveData<State>

typealias MutableStateLiveData = MutableLiveData<State>
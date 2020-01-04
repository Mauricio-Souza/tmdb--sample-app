package com.msousa.tmdbredux

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msousa.tmdbredux.redux.state.State

typealias StringResource = R.string

typealias NavigateRouter<reified T> = () -> Class<T>

typealias StateLiveData = LiveData<State>

typealias MutableStateLiveData = MutableLiveData<State>
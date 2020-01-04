package com.msousa.todolistsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msousa.todolistsample.redux.state.State

typealias StringResource = R.string

typealias NavigateRouter<reified T> = () -> Class<T>

typealias StateLiveData = LiveData<State>

typealias MutableStateLiveData = MutableLiveData<State>
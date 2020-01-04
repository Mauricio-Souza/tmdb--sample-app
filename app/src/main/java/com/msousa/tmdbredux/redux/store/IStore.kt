package com.msousa.tmdbredux.redux.store

import com.msousa.tmdbredux.StateLiveData
import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.state.State

interface IStore {

    val stateLiveData: StateLiveData

    fun getState() : State
    fun dispatcher(action: Action)
}
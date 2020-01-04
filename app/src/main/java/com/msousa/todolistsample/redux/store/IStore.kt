package com.msousa.todolistsample.redux.store

import com.msousa.todolistsample.StateLiveData
import com.msousa.todolistsample.redux.actions.Action
import com.msousa.todolistsample.redux.state.State

interface IStore {

    val stateLiveData: StateLiveData

    fun getState() : State
    fun dispatcher(action: Action)
}
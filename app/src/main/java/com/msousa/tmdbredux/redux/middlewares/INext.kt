package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action

interface INext {

    suspend fun onNext(action: Action): Action
}
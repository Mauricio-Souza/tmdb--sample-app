package com.msousa.todolistsample.redux.middlewares

import com.msousa.todolistsample.redux.actions.Action

interface INext {

    suspend fun onNext(action: Action, next: INext): Action
}
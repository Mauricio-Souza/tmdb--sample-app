package com.msousa.todolistsample.redux.middlewares

import com.msousa.todolistsample.redux.actions.Action
import com.msousa.todolistsample.redux.actions.DatabaseOperation
import com.msousa.todolistsample.data.local.ITMDbDatabaseRepository

class DatabaseMiddleware(private val databaseRepository: ITMDbDatabaseRepository) : INext {

    override suspend fun onNext(action: Action, next: INext): Action {
        var newAction = action
        when (action) {
            is DatabaseOperation.Insert<*> -> {

            }
            is DatabaseOperation.Update<*> -> {

            }
            is DatabaseOperation.Delete<*> -> {

            }
            is DatabaseOperation.Select<*> -> {

            }
        }
        return newAction
    }
}
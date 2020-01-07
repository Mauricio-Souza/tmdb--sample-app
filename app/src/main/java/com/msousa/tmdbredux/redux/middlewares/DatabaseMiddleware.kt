package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.DatabaseOperation
import com.msousa.tmdbredux.data.local.ITMDbDatabaseRepository

class DatabaseMiddleware(private val databaseRepository: ITMDbDatabaseRepository) : INext {

    override suspend fun onNext(action: Action): Action {
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
            else -> { }
        }
        return newAction
    }
}
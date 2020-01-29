package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.redux.actions.Database
import com.msousa.tmdbredux.redux.actions.Database.Operation.UPDATE
import com.msousa.tmdbredux.redux.actions.Database.Operation.DELETE
import com.msousa.tmdbredux.redux.actions.Database.Operation.SELECT
import com.msousa.tmdbredux.redux.actions.Database.Operation.INSERT
import com.msousa.tmdbredux.data.local.ITMDbDatabaseRepository

class DatabaseMiddleware(private val repository: ITMDbDatabaseRepository) : INext {

    override suspend fun onNext(action: Action): Action {
        var newAction = action
        when (action) {
            is Database.Entity<*> -> {
                when (action.operation) {
                    SELECT -> { repository.select(param = action.param) }
                    DELETE -> { repository.delete(param = action.param) }
                    INSERT -> { repository.insert(param = action.param) }
                    UPDATE -> { repository.update(param = action.param) }
                }
            }
        }
        return newAction
    }
}
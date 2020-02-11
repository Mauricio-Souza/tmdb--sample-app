package com.msousa.tmdbredux.redux.middlewares

import com.msousa.tmdbredux.redux.actions.Action
import com.msousa.tmdbredux.data.local.ITMDbDatabaseDataSource
import com.msousa.tmdbredux.presentation.models.mapper.toVO
import com.msousa.tmdbredux.redux.actions.FromDatabase.SelectAllMovies
import com.msousa.tmdbredux.redux.actions.FromDatabase.InsertMovieDetails
import com.msousa.tmdbredux.redux.actions.FromDatabase.InsertMovies
import com.msousa.tmdbredux.redux.actions.FromDatabase.SelectMovieDetails
import com.msousa.tmdbredux.redux.actions.Result

class DatabaseMiddleware(private val dataSource: ITMDbDatabaseDataSource) : INext {

    override suspend fun onNext(action: Action): Action {
        var newAction = action
        when (action) {
            is InsertMovies -> {
                runDatabaseOperation(
                    onExecute = { dataSource.insertMovies(*action.movies) },
                    onSuccess = { entity -> newAction = Result.Success(entity) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO())}
                )
            }
            is InsertMovieDetails -> { }
            is SelectAllMovies -> { }
            is SelectMovieDetails -> { }
        }
        return newAction
    }
}
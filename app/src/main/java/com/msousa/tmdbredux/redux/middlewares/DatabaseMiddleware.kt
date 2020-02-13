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
                    onExecute = { dataSource.insertMovies(action.movies) },
                    onSuccess = { newAction = Result.Success(action.movies.toVO()) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO())}
                )
            }
            is InsertMovieDetails -> {
                runDatabaseOperation(
                    onExecute = { dataSource.insertMovieDetails(action.movieEntity) },
                    onSuccess = { newAction = Result.Success(action.movieEntity.toVO()) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO()) }
                )
            }
            is SelectAllMovies -> {
                runDatabaseOperation(
                    onExecute = { dataSource.selectAllMovies() },
                    onSuccess = { entity -> newAction = Result.Success(entity.toVO()) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO()) }
                )
            }
            is SelectMovieDetails -> {
                runDatabaseOperation(
                    onExecute = { dataSource.selectMovieDetails(action.movieId) },
                    onSuccess = { entity -> newAction = Result.Success(entity.toVO()) },
                    onFailure = { error -> newAction = Result.Failure(error.toVO()) }
                )
            }
        }
        return newAction
    }
}
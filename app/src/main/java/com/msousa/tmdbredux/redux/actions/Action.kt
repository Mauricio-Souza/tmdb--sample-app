package com.msousa.tmdbredux.redux.actions

import android.content.Context
import com.msousa.tmdbredux.NavigationRouter
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity
import com.msousa.tmdbredux.presentation.MovieDetailsActivity
import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO

sealed class Action

sealed class Result : Action() {

    data class Success<out R>(val data: R) : Result()
    data class Failure(val error: ErrorMessageVO) : Result()
    object Loading : Result()
}

sealed class ViewAction : Action() {

    data class OnListItemClicked(val context: Context, val id: String) : NavigationRouter, ViewAction() {
        override fun invoke() = MovieDetailsActivity.getIntent(context, id)
    }

    object OnMainActivityCreated : ViewAction()
    data class OnMovieDetailsActivityCreated(val movieId: String) : ViewAction()
}

sealed class FromDatabase : Action() {

    object SelectAllMovies : FromDatabase()
    data class InsertMovies(val movies: Array<out MoviesEntity>) : FromDatabase()
    data class SelectMovieDetails(val movieId: Long) : FromDatabase()
    data class InsertMovieDetails(val movieEntity: MovieDetailsEntity) : FromDatabase()
}
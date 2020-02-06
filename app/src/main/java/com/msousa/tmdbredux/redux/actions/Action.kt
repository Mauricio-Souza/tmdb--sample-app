package com.msousa.tmdbredux.redux.actions

import android.content.Context
import com.msousa.tmdbredux.NavigationRouter
import com.msousa.tmdbredux.presentation.MovieDetailsActivity
import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO

sealed class Action

sealed class ServerResponse : Action() {

    data class Success<out R>(val data: R) : ServerResponse()
    data class Failure(val error: ErrorMessageVO) : ServerResponse()
    object Loading : ServerResponse()
}

sealed class ViewAction : Action() {

    data class OnListItemClicked(val context: Context, val id: String) : NavigationRouter, ViewAction() {
        override fun invoke() = MovieDetailsActivity.getIntent(context, id)
    }

    object OnMainActivityCreated : ViewAction()
    data class OnMovieDetailsActivityCreated(val movieId: String) : ViewAction()
}

sealed class DatabaseOperation : Action() {

    data class Select<P>(val param: P? = null) : DatabaseOperation()
    class Insert<P>(vararg val param: P) : DatabaseOperation()
}
package com.msousa.tmdbredux.redux.actions

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.msousa.tmdbredux.NavigationRouter
import com.msousa.tmdbredux.data.local.entities.MovieDetailsEntity
import com.msousa.tmdbredux.data.local.entities.MoviesEntity
import com.msousa.tmdbredux.data.remote.exceptions.TMDbException
import com.msousa.tmdbredux.presentation.screens.error.NoInternetConnectivityFragment
import com.msousa.tmdbredux.presentation.screens.details.MovieDetailsActivity
import com.msousa.tmdbredux.presentation.screens.error.NoSuchDataFoundFragment

sealed class Action

sealed class Result : Action() {

    data class Success<out R>(val data: R) : Result()
    data class Failure(val error: TMDbException) : Result()
    object Loading : Result()
}

sealed class ViewAction : Action() {
    object FetchMovieList : ViewAction()
    data class FetchMovieDetails(val movieId: String) : ViewAction()
}

sealed class NavigateAction : Action() {
    data class OpenMovieDetailsScreen(val context: Context, val id: String) : NavigationRouter<Intent>, NavigateAction() {
        override fun invoke() = MovieDetailsActivity.getIntent(context, id)
    }

    object ShowNoSuchDataFoundScreen : NavigationRouter<Fragment>, NavigateAction() {
        override fun invoke() = NoSuchDataFoundFragment.getInstance()
    }

    object ShowNoInternetConnectionScreen : NavigationRouter<Fragment>, NavigateAction() {
        override fun invoke() = NoInternetConnectivityFragment.getInstance()
    }
}

sealed class FromDatabase : Action() {

    object SelectAllMovies : FromDatabase()
    data class InsertMovies(val movies: MoviesEntity) : FromDatabase()
    data class SelectMovieDetails(val movieId: Long) : FromDatabase()
    data class InsertMovieDetails(val movieEntity: MovieDetailsEntity) : FromDatabase()
}
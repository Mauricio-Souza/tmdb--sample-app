package com.msousa.todolistsample.redux.actions

import com.msousa.todolistsample.NavigateRouter
import com.msousa.todolistsample.presentation.HomeActivity
import com.msousa.todolistsample.presentation.models.viewObjects.ErrorMessageVO

sealed class Action

sealed class ServerResponse : Action() {

    data class Success<out R>(val data: R) : ServerResponse()
    data class Failure(val error: ErrorMessageVO) : ServerResponse()
}

sealed class ServerRequest : Action() {

    data class GetMovieDetails(val movieId: String) : ServerRequest()
    object Authenticate : ServerRequest()
    object GetMovies : ServerRequest()
}

sealed class DatabaseOperation : Action() {

    data class Insert<T>(val data: T) : DatabaseOperation()
    data class Update<T>(val data: T) : DatabaseOperation()
    data class Delete<T>(val data: T) : DatabaseOperation()
    data class Select<T>(val data: T) : DatabaseOperation()
}

sealed class NavigationAction : Action() {

    object ToHome : NavigateRouter<HomeActivity>, NavigationAction() {
        override fun invoke() = HomeActivity::class.java
    }
}

sealed class AppBehaviorAction : Action() {
    
    object DisplayLoading: AppBehaviorAction()
    data class DisplayError(val e: Exception) : AppBehaviorAction()
}
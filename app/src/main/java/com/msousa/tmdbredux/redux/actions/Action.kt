package com.msousa.tmdbredux.redux.actions

import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO

sealed class Action

sealed class ServerResponse : Action() {

    data class Success<out R>(val data: R) : ServerResponse()
    data class Failure(val error: ErrorMessageVO) : ServerResponse()
}

sealed class ViewAction : Action() {

    data class OnMovieClicked(val movieId: String) : ViewAction()
    object OnLoginButtonClicked : ViewAction()
    object OnMainActivityCreated : ViewAction()
}

sealed class DatabaseOperation : Action() {

    data class Insert<T>(val data: T) : DatabaseOperation()
    data class Update<T>(val data: T) : DatabaseOperation()
    data class Delete<T>(val data: T) : DatabaseOperation()
    data class Select<T>(val data: T) : DatabaseOperation()
}

sealed class AppBehaviorAction : Action() {
    
    object DisplayLoading: AppBehaviorAction()
    data class DisplayError(val message: String) : AppBehaviorAction()
}
package com.msousa.todolistsample.redux.store

import com.msousa.todolistsample.MutableStateLiveData
import com.msousa.todolistsample.StateLiveData
import com.msousa.todolistsample.redux.actions.*
import com.msousa.todolistsample.redux.middlewares.IMiddleware
import com.msousa.todolistsample.redux.reducer.ServerResponseReducer
import com.msousa.todolistsample.redux.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Store(
    private val mainMiddleware: IMiddleware
) : BaseStore(), IStore {

    private var currentState: State = State(Unit)

    private val _stateLiveData = MutableStateLiveData()
    override val stateLiveData: StateLiveData
        get() = _stateLiveData

    override fun getState(): State = currentState

    override fun dispatcher(action: Action) {
        uiScope.launch {
            var newAction = action
            withContext(Dispatchers.IO) {
                newAction = middleware(action)
            }
            currentState = reducer(newAction)
            _stateLiveData.value = currentState
        }
    }

    private suspend fun middleware(action: Action): Action {
        var newAction = action
        mainMiddleware.apply(action).collect { action ->
            newAction = action
        }
        return newAction
    }

    private suspend fun reducer(action: Action): State {
        var newState = currentState
        when (action) {
            is ServerResponse -> {
                ServerResponseReducer.apply(currentState, action).collect { state ->
                    newState = state
                }
            }
            is NavigationAction -> { }
            is AppBehaviorAction -> { }
        }
        return newState
    }
}
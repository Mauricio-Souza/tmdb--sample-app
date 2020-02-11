package com.msousa.tmdbredux.redux.store

import com.msousa.tmdbredux.MutableStateLiveData
import com.msousa.tmdbredux.StateLiveData
import com.msousa.tmdbredux.redux.actions.*
import com.msousa.tmdbredux.redux.actions.Result.Loading
import com.msousa.tmdbredux.redux.middlewares.IMiddleware
import com.msousa.tmdbredux.redux.reducer.NavigationReducer
import com.msousa.tmdbredux.redux.reducer.ResultReducer
import com.msousa.tmdbredux.redux.state.State
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
        _stateLiveData.postValue(currentState.copy(data = Loading))
        mainMiddleware.apply(action).collect { action ->
            newAction = action
        }
        return newAction
    }

    private suspend fun reducer(action: Action): State {
        var newState = currentState
        when (action) {
            is Result -> {
                ResultReducer.apply(currentState, action).collect { state ->
                    newState = state
                }
            }
            is ViewAction -> {
                NavigationReducer.apply(currentState, action).collect { state ->
                    newState = state
                }
            }
        }
        return newState
    }
}
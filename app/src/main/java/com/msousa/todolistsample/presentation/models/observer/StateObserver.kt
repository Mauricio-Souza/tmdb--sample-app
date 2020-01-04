package com.msousa.todolistsample.presentation.models.observer

import androidx.lifecycle.Observer
import com.msousa.todolistsample.redux.state.State
import timber.log.Timber

class StateObserver<T>(
    private val consumer: (T?) -> Unit
) : Observer<State> {
    @Suppress("UNCHECKED_CAST")
    override fun onChanged(state: State?) {
        state?.let {
            try {
                consumer(it.data as T)
            } catch (e: ClassCastException) {
                Timber.e(e)
            }
        }
    }
}
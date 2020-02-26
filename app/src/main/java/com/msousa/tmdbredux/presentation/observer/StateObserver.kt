package com.msousa.tmdbredux.presentation.observer

import android.view.View
import androidx.lifecycle.Observer
import com.msousa.tmdbredux.redux.actions.Result.Loading
import com.msousa.tmdbredux.redux.state.State
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

class LoadingObserver(
    private val consumer: (Int) -> Unit
) : Observer<State> {
    override fun onChanged(state: State?) {
        state?.let {
            if (it.data is Loading) consumer(View.VISIBLE)
            else consumer(View.GONE)
        }
    }

}
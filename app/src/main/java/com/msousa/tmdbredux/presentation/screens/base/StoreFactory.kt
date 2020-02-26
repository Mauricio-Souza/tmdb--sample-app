package com.msousa.tmdbredux.presentation.screens.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msousa.tmdbredux.redux.middlewares.ISideEffect
import com.msousa.tmdbredux.redux.store.Store

class StoreFactory(
    private val sideEffect: ISideEffect
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Store(sideEffect) as T
    }

}
package com.msousa.todolistsample.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msousa.todolistsample.redux.middlewares.IMiddleware
import com.msousa.todolistsample.redux.store.Store

class StoreFactory(
    private val mainMiddleware: IMiddleware
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return Store(mainMiddleware) as T
    }

}
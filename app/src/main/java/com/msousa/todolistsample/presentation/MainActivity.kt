package com.msousa.todolistsample.presentation

import android.content.Intent
import android.os.Bundle
import com.msousa.todolistsample.NavigateRouter
import com.msousa.todolistsample.R
import com.msousa.todolistsample.redux.actions.ServerRequest.*
import com.msousa.todolistsample.presentation.models.observer.StateObserver
import com.msousa.todolistsample.presentation.models.viewObjects.ErrorMessageVO

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        store.dispatcher(Authenticate)

        store.stateLiveData.observe(this, StateObserver<NavigateRouter<*>> { destiny ->
            startActivity(Intent(this, destiny?.invoke()))
        })

        store.stateLiveData.observe(this, StateObserver<ErrorMessageVO> { error ->
            showShortToast(error?.message)
        })

    }
}
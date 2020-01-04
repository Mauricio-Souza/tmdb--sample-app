package com.msousa.tmdbredux.presentation

import android.content.Intent
import android.os.Bundle
import com.msousa.tmdbredux.NavigateRouter
import com.msousa.tmdbredux.redux.actions.ServerRequest.*
import com.msousa.tmdbredux.presentation.models.observer.StateObserver
import com.msousa.tmdbredux.presentation.models.viewObjects.ErrorMessageVO
import com.msousa.tmdbredux.R

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
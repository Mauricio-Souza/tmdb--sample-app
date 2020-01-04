package com.msousa.tmdbredux.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import com.msousa.tmdbredux.redux.store.IStore
import com.msousa.tmdbredux.redux.store.Store
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.erased.instance

abstract class BaseActivity : AppCompatActivity(), LifecycleOwner, KodeinAware {
    override val kodein by kodein()

    private lateinit var lifecycleRegistry: LifecycleRegistry
    protected lateinit var store: IStore

    private val storeFactory: StoreFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleRegistry = LifecycleRegistry(this)

        store = ViewModelProviders.of(this, storeFactory).get(Store::class.java)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}
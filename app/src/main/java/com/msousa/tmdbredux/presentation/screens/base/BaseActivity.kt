package com.msousa.tmdbredux.presentation.screens.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
    lateinit var store: IStore

    private val storeFactory: StoreFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        lifecycleRegistry = LifecycleRegistry(this)

        store = ViewModelProviders.of(this, storeFactory).get(Store::class.java)
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry

    protected fun showActionBarWithTitle(title: String) {
        supportActionBar?.apply {
            show()
            this.title = title
        }
    }

    protected fun showActionBarWithBackButton() {
        supportActionBar?.apply {
            show()
            setDisplayHomeAsUpEnabled(true)
        }
    }

    protected fun replaceFragment(fragment: Fragment, container: Int, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager?.beginTransaction()?.apply {
            replace(container, fragment, fragment::class.java.simpleName)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }
        transaction?.commitAllowingStateLoss()
    }
}
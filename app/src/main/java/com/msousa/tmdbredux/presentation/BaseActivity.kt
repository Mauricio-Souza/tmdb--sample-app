package com.msousa.tmdbredux.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders
import com.msousa.tmdbredux.StringResource
import com.msousa.tmdbredux.presentation.models.observer.LoadingObserver
import com.msousa.tmdbredux.redux.store.IStore
import com.msousa.tmdbredux.redux.store.Store
import kotlinx.android.synthetic.main.activity_movie_details.*
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

    protected fun loadingObserverBehavior(viewId: Int) = LoadingObserver { isLoading ->
        check(viewId != 0) { getString(StringResource.INVALID_RESOURCE_ID) }
        findViewById<ProgressBar>(viewId)?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
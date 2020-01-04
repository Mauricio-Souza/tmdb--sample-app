package com.msousa.todolistsample

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.msousa.todolistsample.data.local.ITMDbDatabaseRepository
import com.msousa.todolistsample.data.local.TMDbDatabaseRepository
import com.msousa.todolistsample.data.remote.ITMDbRepository
import com.msousa.todolistsample.data.remote.TMDbRepository
import com.msousa.todolistsample.redux.middlewares.*
import com.msousa.todolistsample.presentation.StoreFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class TMBbApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@TMBbApplication))

        bind<ITMDbRepository>() with singleton { TMDbRepository() }
        bind<ITMDbDatabaseRepository>() with singleton { TMDbDatabaseRepository() }
        bind() from singleton { ServerMiddleware(instance()) }
        bind() from singleton { DatabaseMiddleware(instance()) }
        bind() from singleton { EndOfChain() }
        bind<IMiddleware>() with singleton {
            Middleware(
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager,
                listOf(
                    instance<ServerMiddleware>(),
                    instance<DatabaseMiddleware>(),
                    instance<EndOfChain>()
                )
            )
        }
        bind() from provider { StoreFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
    }
}
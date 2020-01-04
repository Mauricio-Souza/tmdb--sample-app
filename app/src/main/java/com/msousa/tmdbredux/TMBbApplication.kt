package com.msousa.tmdbredux

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.msousa.tmdbredux.data.local.ITMDbDatabaseRepository
import com.msousa.tmdbredux.data.local.TMDbDatabaseRepository
import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.TMDbRepository
import com.msousa.tmdbredux.redux.middlewares.*
import com.msousa.tmdbredux.presentation.StoreFactory
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
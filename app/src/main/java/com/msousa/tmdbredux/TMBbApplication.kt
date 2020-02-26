package com.msousa.tmdbredux

import android.app.Application
import com.msousa.tmdbredux.data.local.ITMDbDatabaseDataSource
import com.msousa.tmdbredux.data.local.TMDbDatabaseDataSource
import com.msousa.tmdbredux.data.local.TMDbDatabaseProvider
import com.msousa.tmdbredux.data.remote.ITMDbRepository
import com.msousa.tmdbredux.data.remote.TMDbRepository
import com.msousa.tmdbredux.redux.middlewares.*
import com.msousa.tmdbredux.presentation.screens.base.StoreFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class TMBbApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@TMBbApplication))

        bind() from singleton { TMDbDatabaseProvider(instance()) }
        bind() from singleton { instance<TMDbDatabaseProvider>().moviesDao() }
        bind() from singleton { instance<TMDbDatabaseProvider>().moviesDetailsDao() }
        bind<ITMDbRepository>() with singleton { TMDbRepository() }
        bind() from singleton { ServerMiddleware(instance()) }
        bind() from singleton { DatabaseMiddleware(instance()) }
        bind() from singleton { EndOfChain() }
        bind<ITMDbDatabaseDataSource>() with singleton {
            TMDbDatabaseDataSource(instance(), instance())
        }
        bind<ISideEffect>() with singleton {
            SideEffect(
                listOf(
                    instance<ServerMiddleware>(),
                    instance<DatabaseMiddleware>(),
                    instance<EndOfChain>()
                )
            )
        }
        bind() from singleton { StoreFactory(instance()) }
    }
}
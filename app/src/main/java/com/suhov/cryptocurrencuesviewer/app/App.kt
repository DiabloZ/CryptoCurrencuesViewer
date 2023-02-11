package com.suhov.cryptocurrencuesviewer.app

import android.app.Application
import android.content.Context
import com.suhov.cryptocurrencuesviewer.modules.*
import com.suhov.cryptocurrencuesviewer.view.temp.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(
                    dataRepository,
                    listViewModel,
                    listAdapter,
                    listRepository,
                    detailsViewModel,
                    detailsAdapter,
                    detailRepository
                    ))
        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }
}

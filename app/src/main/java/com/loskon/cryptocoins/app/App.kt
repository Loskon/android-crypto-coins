package com.loskon.cryptocoins.app

import android.app.Application
import com.loskon.cryptocoins.BuildConfig
import com.loskon.cryptocoins.app.coinlist.coinListModule
import com.loskon.cryptocoins.data.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initializeKoin(this)
    }

    private fun initializeKoin(application: Application) {
        startKoin {
            androidContext(application)
            modules(listOf(networkModule, coinListModule))
        }
    }
}
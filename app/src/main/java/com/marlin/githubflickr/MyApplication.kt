package com.marlin.githubflickr

import android.app.Application
import com.marlin.githubflickr.di.apiModule
import com.marlin.githubflickr.di.repositoryModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber



class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(repositoryModule, apiModule))

        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
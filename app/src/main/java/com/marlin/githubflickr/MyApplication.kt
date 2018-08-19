package com.marlin.githubflickr

import android.app.Application
import com.marlin.githubflickr.di.repositoryModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(repositoryModule))
    }
}
package de.handler.core

import android.app.Application
import org.koin.android.ext.android.startKoin

class BabylonPostsApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        val modules = listOf(
            networkModule,
            serviceModule,
            providerModule,
            repositoryModule
        )

        startKoin(this, modules)
    }
}
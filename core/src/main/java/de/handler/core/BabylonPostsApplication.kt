package de.handler.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BabylonPostsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            printLogger()
            androidContext(this@BabylonPostsApplication)
            modules(
                listOf(
                    networkModule,
                    serviceModule,
                    providerModule,
                    repositoryModule
                )
            )
        }

    }
}
package de.handler.core

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import de.handler.core.provider.Provider
import de.handler.core.provider.ProviderImpl
import de.handler.core.repository.Repository
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

val repositoryModule = module {
    single<Repository> { Repository(get()) }
}

val providerModule = module {
    single<Provider> { ProviderImpl(get()) }
}

val networkModule = module {
    single<OkHttpClient>() {
        val cacheSize = 100 * 1024 * 1024 // 500 MiB
        val cacheDirectory = File(androidApplication().cacheDir, "responses")
        val cache = Cache(cacheDirectory, cacheSize.toLong())

        OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    single<Retrofit>() {
        Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    single<Moshi> { Moshi.Builder().build() }
}

val serviceModule = module {
    single<Service> { get<Retrofit>().create(Service::class.java) }
}
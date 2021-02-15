package com.example.lowescodingexercise

import android.app.Application
import com.example.lowescodingexercise.model.api.ForecastService
import com.example.lowescodingexercise.model.repository.abstraction.ForecastRepository
import com.example.lowescodingexercise.model.repository.implementation.ForecastRepositoryImpl
import com.example.lowescodingexercise.viewmodel.ForecastViewModel
import com.google.gson.Gson
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                retrofitModule,
                repositoryModule,
                viewModelModule
            )
        }
    }

    private val retrofitModule = module {

        single {
            val cacheSize: Long = 10 * 1024 * 1024 // 10mb
            val mCache = Cache(cacheDir, cacheSize)
            OkHttpClient().newBuilder()
                .cache(mCache)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level =
                        HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        single { Gson() }

        single {
            Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }

        single { get<Retrofit>().create(ForecastService::class.java) }

    }

    private val repositoryModule = module {
        factory<ForecastRepository> { ForecastRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModel { ForecastViewModel(get()) }
    }

}
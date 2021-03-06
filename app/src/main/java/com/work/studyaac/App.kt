package com.work.studyaac

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.work.studyaac.di.dataSourceModule
import com.work.studyaac.di.networkModule
import com.work.studyaac.di.repositoryModule
import com.work.studyaac.di.viewModelModule
import com.work.studyaac.network.api.ApiService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    // DEPENDENCIES
    private val okHttpClient: OkHttpClient by lazy {
        val okHttpBuilder = OkHttpClient.Builder()

        okHttpBuilder
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("X-Api-Key", "35454ae809cb4e1dbfe0bb8fc074d684")
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }
    private val gson: Gson by lazy {
        GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .setPrettyPrinting()
            .create()
    }
    val retorfit: Retrofit by lazy {

        val url = "https://newsapi.org/v2/"

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .client(okHttpClient)
            .build()
    }

    // SERVICES
    val apiService: ApiService by lazy {
        ApiService.create()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    dataSourceModule,
                    repositoryModule,
                    networkModule
                )
            )
        }

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
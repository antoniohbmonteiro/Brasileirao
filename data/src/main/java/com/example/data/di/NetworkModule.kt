package com.example.data.di

import com.example.data.BuildConfig
import com.example.data.base.baseUrl
import com.example.data.repositories.BrasileiraoRemoteRepositoryImpl
import com.example.data.service.BrasileiraoService
import com.example.domain.repositories.BrasileiraoRemoteRepository
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<Call.Factory> { okHttp() }

    single { retrofit(get(), baseUrl) }

    single<BrasileiraoService> { get<Retrofit>().create(BrasileiraoService::class.java) }

    single<BrasileiraoRemoteRepository> { BrasileiraoRemoteRepositoryImpl(get()) }
}
private val interceptor: Interceptor
    get() = HttpLoggingInterceptor()
        .apply {
        level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
}

fun okHttp() = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

fun retrofit(callFactory: Call.Factory, baseUrl: String): Retrofit = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

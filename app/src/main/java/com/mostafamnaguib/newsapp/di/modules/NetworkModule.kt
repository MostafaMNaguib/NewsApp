package com.mostafamnaguib.newsapp.di.modules

import com.mostafamnaguib.newsapp.di.createOkHttpClient
import com.mostafamnaguib.newsapp.di.createService
import com.mostafamnaguib.newsapp.di.moshi
import com.mostafamnaguib.newsapp.network.ApiServices
import com.mostafamnaguib.newsapp.utils.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(get()))
    }

    factory { createOkHttpClient() }
    single { moshi() }

    single {
        createService<ApiServices>(BASE_URL, get(), get())
    }
}
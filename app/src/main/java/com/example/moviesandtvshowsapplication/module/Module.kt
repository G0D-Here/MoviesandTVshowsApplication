package com.example.moviesandtvshowsapplication.module

import com.example.moviesandtvshowsapplication.WatchModeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.watchmode.com/v1/"

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideRetrofitInstance(): WatchModeApi =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava3CallAdapterFactory.create()
            )
            .build().create(WatchModeApi::class.java)
}
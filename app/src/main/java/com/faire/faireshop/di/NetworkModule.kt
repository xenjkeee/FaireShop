package com.faire.faireshop.di

import com.faire.faireshop.data.network.adapter.ResultCallAdapterFactory
import com.faire.faireshop.data.network.api.FaireShopService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://cdn.faire.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): FaireShopService =
        retrofit.create(FaireShopService::class.java)
}
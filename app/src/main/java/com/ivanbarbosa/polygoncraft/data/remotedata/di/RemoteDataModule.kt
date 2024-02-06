package com.ivanbarbosa.polygoncraft.data.remotedata.di

import com.ivanbarbosa.polygoncraft.data.remotedata.ApiService
import com.ivanbarbosa.polygoncraft.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/* 
* Project: PolygonCraft
* From: com.ivanbarbosa.polygoncraft.data.remotedata.di
* Create by Ivan Barbosa on 6/02/2024 at 4:07 p.m.
* Linkedin: https://www.linkedin.com/in/ivanbarbosaortega/
*/
@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
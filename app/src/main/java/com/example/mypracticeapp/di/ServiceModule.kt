package com.example.mypracticeapp.di

import com.example.data.service.GiphyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Provides
    fun provideGiphyService(retrofit: Retrofit): GiphyService {
        return retrofit.create(GiphyService::class.java)
    }
}
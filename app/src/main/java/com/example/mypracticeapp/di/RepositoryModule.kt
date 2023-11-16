package com.example.mypracticeapp.di

import com.example.data.repositories.GiphyRepoImpl
import com.example.domain.repositories.GiphyRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideGiphyRepo(impl: GiphyRepoImpl): GiphyRepo
}
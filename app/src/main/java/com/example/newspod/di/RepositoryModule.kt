package com.example.newspod.di

import com.example.newspod.data.repository.NewsRepositoryImpl
import com.example.newspod.data.web.NewsApiInterface
import com.example.newspod.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(api:NewsApiInterface):NewsRepository{
        return NewsRepositoryImpl(api)
    }
}
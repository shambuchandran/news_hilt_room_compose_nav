package com.example.newspod.di

import android.content.Context
import com.example.newspod.data.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context):NewsDatabase{
        return NewsDatabase.getDatabase(context)
    }
}
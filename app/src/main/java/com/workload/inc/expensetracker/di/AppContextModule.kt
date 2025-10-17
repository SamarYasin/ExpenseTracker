package com.workload.inc.expensetracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppContextModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context
}
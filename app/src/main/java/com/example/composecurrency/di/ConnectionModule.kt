package com.example.composecurrency.di

import android.content.Context
import com.example.data.remote.connection.ConnectionImpl
import com.example.domain.connection.Connection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectionModule {

    @Provides
    @Singleton
    fun provideConnection(
        @ApplicationContext context: Context
    ): Connection = ConnectionImpl(context)
}
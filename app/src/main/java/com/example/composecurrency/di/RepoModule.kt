package com.example.composecurrency.di

import com.example.data.AppRepository
import com.example.data.local.LocalDataSource
import com.example.data.remote.ApiService
import com.example.data.remote.RemoteDataSource
import com.example.data.remote.database.FavoritesDao
import com.example.domain.gateway.AppGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    @Singleton
    fun provideAppGateway(
        appRepository: AppRepository
    ): AppGateway = appRepository

//    @Provides
//    @Singleton
//    fun provideRemoteDataSource(
//        apiService: ApiService
//    ): RemoteDataSource = RemoteDataSource(apiService)
//
//    @Provides
//    @Singleton
//    fun provideLocalDataSource(
//        favoritesDao: FavoritesDao
//    ): LocalDataSource = LocalDataSource(favoritesDao)
}
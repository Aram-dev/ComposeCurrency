package com.example.data

import com.example.data.local.LocalDataSource
import com.example.data.remote.RemoteDataSource
import com.example.data.remote.asFavoriteEntity
import com.example.data.remote.asFavorites
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.entity.Rate
import com.example.domain.gateway.AppGateway
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AppGateway {

    override suspend fun getCurrencies(base: String): Response<CurrencyRates> =
            remoteDataSource.getCurrencies(base)

    override suspend fun getFavorites(): Flow<List<Favorite>> = flow {
        localDataSource.getFavorites().collect {
            emit(it.asFavorites())
        }
    }

    override suspend fun getFavoriteItemRate(base: String, symbols: String): Response<CurrencyRates>  =
        remoteDataSource.getFavoriteItemRate(base, symbols)

    override suspend fun addToFavorites(favorite: Favorite) = localDataSource.addFavorite(favorite.asFavoriteEntity())

    override suspend fun isFavorites(from: String, to: String): Boolean = localDataSource.isFavorite(from, to)

    override suspend fun deleteFromFavorites(favorite: Favorite) {
        val entity = favorite.asFavoriteEntity()
        localDataSource.deleteFavorite(entity.currencyCodeFrom, entity.currencyCodeTo)
    }
}
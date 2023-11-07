package com.example.data.remote

import com.example.data.remote.database.FavoriteEntity
import com.example.domain.entity.CurrencyRates
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCurrencies(base: String): Response<CurrencyRates> = apiService.getCurrencies(base).asCurrencies()

    suspend fun getFavoriteItemRate(base: String, symbols: String): Response<CurrencyRates> =
        apiService.getCurrency(base, symbols).asCurrencies()
}
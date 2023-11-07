package com.example.domain.gateway

import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.entity.Rate
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AppGateway {
    suspend fun getCurrencies(base: String): Response<CurrencyRates>
    suspend fun getFavorites(): Flow<List<Favorite>>
    suspend fun getFavoriteItemRate(base: String, symbols: String): Response<CurrencyRates>
    suspend fun addToFavorites(favorite: Favorite)
    suspend fun isFavorites(from: String, to: String): Boolean
    suspend fun deleteFromFavorites(favorite: Favorite)
}
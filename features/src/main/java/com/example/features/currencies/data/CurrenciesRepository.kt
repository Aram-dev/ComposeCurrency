package com.example.features.currencies.data

import com.example.domain.connection.Connection
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.gateway.RepositoryBase
import com.example.domain.usecase.AddToFavoritesUseCase
import com.example.domain.usecase.CurrenciesUseCase
import com.example.domain.usecase.IsFavoriteUseCase
import com.example.domain.usecase.RemoveFromFavoritesUseCase
import com.example.domain.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrenciesRepository @Inject constructor(
    private val connection: Connection,
    private val currenciesUseCase: CurrenciesUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
) : RepositoryBase() {

    override val connectivity: Connection
        get() = connection

    suspend fun currencies(base: String): Flow<ViewState<CurrencyRates>> = fetchData {
        currenciesUseCase.invoke(base)
    }

    suspend fun addToFavorites(favorite: Favorite) = addToFavoritesUseCase.invoke(favorite)

    suspend fun removeFromFavorites(favorite: Favorite) = removeFromFavoritesUseCase.invoke(favorite)

    fun isFavorite(from: String, to: String): Boolean {
        val isFavorite = runBlocking(Dispatchers.IO) {
            isFavoriteUseCase.invoke(from, to)
        }
        return isFavorite
    }
}
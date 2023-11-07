package com.example.features.currencies.data

import com.example.domain.connection.Connection
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import com.example.domain.gateway.RepositoryBase
import com.example.domain.usecase.FavoriteItemRateUseCase
import com.example.domain.usecase.GetFavoritesUseCase
import com.example.domain.usecase.RemoveFromFavoritesUseCase
import com.example.domain.util.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

class FavoritesRepository @Inject constructor(
    private val connection: Connection,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getFavoriteItemRateUseCase: FavoriteItemRateUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): RepositoryBase() {

    override val connectivity: Connection
        get() = connection

    suspend fun favorites(): Flow<List<Favorite>> =  getFavoritesUseCase.invoke()

    suspend fun favoriteItemRate(base: String, symbols: String): Flow<ViewState<CurrencyRates>> = fetchData {
        getFavoriteItemRateUseCase.invoke(base, symbols)
    }

    suspend fun removeFromFavorites(favorite: Favorite) = removeFromFavoritesUseCase.invoke(favorite)
}
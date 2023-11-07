package com.example.data.local

import com.example.data.remote.database.FavoriteEntity
import com.example.data.remote.database.FavoritesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoritesDao: FavoritesDao,
)  {

    fun getFavorites(): Flow<List<FavoriteEntity>> = favoritesDao.getAll()

    suspend fun addFavorite(favoriteEntity: FavoriteEntity) = favoritesDao.insert(favoriteEntity)

    suspend fun deleteFavorite(from: String, to: String) = favoritesDao.delete(from, to)

    fun isFavorite(from: String, to: String) = favoritesDao.isRowIsExist(from, to)
}
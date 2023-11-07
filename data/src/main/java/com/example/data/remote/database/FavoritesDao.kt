package com.example.data.remote.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites WHERE id = 1")
    fun get(): FavoriteEntity?

    @Query("SELECT * FROM favorites ORDER BY currencyCodeFrom")
    fun getAll(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: FavoriteEntity)

    @Query("DELETE FROM favorites WHERE currencyCodeFrom LIKE :from AND currencyCodeTo LIKE :to")
    suspend fun delete(from: String, to: String)

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE currencyCodeFrom LIKE :currencyFrom AND currencyCodeTo LIKE :currencyTo)")
    fun isRowIsExist(currencyFrom: String, currencyTo: String): Boolean

    @Query("DELETE FROM favorites")
    suspend fun clear()
}
package com.example.data.remote.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.domain.util.Constant.DATABASE_EXPORT_SCHEMA
import com.example.domain.util.Constant.DATABASE_VERSION

@Database(
    entities = [FavoriteEntity::class],
    version = DATABASE_VERSION,
    exportSchema = DATABASE_EXPORT_SCHEMA
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun favoritesDao(): FavoritesDao
}
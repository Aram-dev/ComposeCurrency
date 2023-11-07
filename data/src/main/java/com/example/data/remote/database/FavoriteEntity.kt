package com.example.data.remote.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val currencyCodeFrom: String,
    val currencyCodeTo: String,
) {
    constructor(currencyCodeFrom: String, currencyCodeTo: String) : this(0, currencyCodeFrom, currencyCodeTo)
}

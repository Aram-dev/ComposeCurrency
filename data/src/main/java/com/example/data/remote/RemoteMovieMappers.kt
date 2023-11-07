package com.example.data.remote

import com.example.data.remote.database.FavoriteEntity
import com.example.data.remote.response.ResponseCurrencyRates
import com.example.domain.entity.CurrencyRates
import com.example.domain.entity.Favorite
import retrofit2.Response

fun Response<ResponseCurrencyRates>.asCurrencies(): Response<CurrencyRates> = Response.success(body()?.let {
    CurrencyRates(
        base = it.base,
        rates = it.rates,
        timestamp = it.timestamp
    )
})

fun List<FavoriteEntity>.asFavorites(): List<Favorite> = this.map {
    Favorite("${it.currencyCodeFrom}/${it.currencyCodeTo}")
}

fun Favorite.asFavoriteEntity(): FavoriteEntity = this.run {
    val data = this.conversionRate.split("/")
    FavoriteEntity(currencyCodeFrom = data.first(), currencyCodeTo = data.last())
}
package com.example.data.remote

import com.example.data.remote.response.ResponseCurrencyRates
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("latest")
    suspend fun getCurrencies(
        @Query("base") base: String,
    ): Response<ResponseCurrencyRates>

    @GET("latest")
    suspend fun getCurrency(
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): Response<ResponseCurrencyRates>
}
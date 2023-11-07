package com.example.domain.entity


import androidx.annotation.Keep

@Keep
data class CurrencyRates(
    val base: String,
    val rates: Map<String, Double>,
    val timestamp: Int
)
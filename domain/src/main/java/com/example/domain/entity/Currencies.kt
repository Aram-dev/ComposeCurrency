package com.example.domain.entity

import androidx.annotation.Keep

@Keep
data class Currencies(
    val success: Boolean,
    val rates: Map<String, Double>
)
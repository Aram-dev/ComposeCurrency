package com.example.domain.usecase

import com.example.domain.entity.CurrencyRates
import com.example.domain.gateway.AppGateway
import retrofit2.Response
import javax.inject.Inject

class FavoriteItemRateUseCase @Inject constructor(
    private val appGateway: AppGateway,
) {

    suspend operator fun invoke(base: String, symbols: String): Response<CurrencyRates> =
        appGateway.getFavoriteItemRate(base, symbols)
}
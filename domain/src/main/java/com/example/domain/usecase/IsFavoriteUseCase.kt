package com.example.domain.usecase

import com.example.domain.gateway.AppGateway
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val appGateway: AppGateway,
) {

    suspend operator fun invoke(from: String, to: String) = appGateway.isFavorites(from, to)
}
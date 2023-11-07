package com.example.domain.usecase

import com.example.domain.entity.Favorite
import com.example.domain.gateway.AppGateway
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val appGateway: AppGateway,
) {

    suspend operator fun invoke(): Flow<List<Favorite>> = appGateway.getFavorites()
}
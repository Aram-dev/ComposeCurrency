package com.example.domain.usecase

import com.example.domain.entity.Favorite
import com.example.domain.gateway.AppGateway
import retrofit2.Response
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val appGateway: AppGateway,
) {

    suspend operator fun invoke(favorite: Favorite) = appGateway.addToFavorites(favorite)
}
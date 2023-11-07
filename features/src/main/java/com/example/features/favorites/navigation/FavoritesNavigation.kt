package com.example.features.favorites.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.features.favorites.ui.FavoritesRoute
import com.example.features.main.navigation.NavigationItem

fun NavGraphBuilder.favoritesScreen() {
    composable(route = NavigationItem.Favorites.route) {
        FavoritesRoute()
    }
}
package com.example.features.main.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navigation
import com.example.features.R
import com.example.features.currencies.navigation.currenciesScreen
import com.example.features.favorites.navigation.favoritesScreen
import com.example.features.filters.navigation.filtersScreen

sealed class NavigationItem(var route: String, val icon: NavigationItemIcon, var title: String) {
    data object Conversions : NavigationItem(
        "Conversions",
        NavigationItemIcon.Currencies,
        "Currencies"
    ) {
        data object Currencies : NavigationItem(
            "Currencies",
            NavigationItemIcon.Filters,
            "Currencies"
        )

        data object Filters : NavigationItem(
            "Filters",
            NavigationItemIcon.Filters,
            "Filters"
        )
    }

    data object Favorites : NavigationItem(
        "Favorites",
        NavigationItemIcon.Favorites,
        "Favorites"
    )
}

sealed class NavigationItemIcon(@DrawableRes val iconRes: Int) {
    data object Currencies : NavigationItemIcon(iconRes = R.drawable.ic_currencies)
    data object Favorites : NavigationItemIcon(iconRes = R.drawable.ic_favorites_on)
    data object Filters : NavigationItemIcon(iconRes = R.drawable.ic_filter)
}

@Composable
fun Navigations(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Conversions.route) {
        navigation(
            route = NavigationItem.Conversions.route,
            startDestination = NavigationItem.Conversions.Currencies.route
        ) {
            currenciesScreen(navController)
            filtersScreen(navController)
        }
        favoritesScreen()
    }
}
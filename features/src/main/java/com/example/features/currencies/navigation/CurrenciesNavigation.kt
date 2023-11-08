package com.example.features.currencies.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.features.currencies.ui.CurrenciesRoute
import com.example.features.main.navigation.NavigationItem

fun NavGraphBuilder.currenciesScreen(navController: NavHostController) {
    composable(route = NavigationItem.Conversions.Currencies.route) {
        CurrenciesRoute(it, navController)
    }
}
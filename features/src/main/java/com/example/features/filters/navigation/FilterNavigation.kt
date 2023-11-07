package com.example.features.filters.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.features.filters.ui.FiltersScreen
import com.example.features.main.navigation.NavigationItem

fun NavGraphBuilder.filtersScreen(navController: NavHostController) {
    composable(route = NavigationItem.Conversions.Filters.route) {
        FiltersScreen(navController)
    }
}
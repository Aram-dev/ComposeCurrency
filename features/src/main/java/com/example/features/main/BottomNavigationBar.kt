package com.example.features.main

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.common.theme.BottomNavIconSelectedColor
import com.example.common.theme.BottomNavIconUnSelectedColor
import com.example.common.theme.LightPrimary
import com.example.common.theme.TextDefault
import com.example.features.main.navigation.NavigationItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Conversions,
        NavigationItem.Favorites,
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    var currentRoute by remember { mutableStateOf(NavigationItem.Conversions.route) }

    items.forEachIndexed { index, navigationItem ->
        if (navigationItem.route == currentRoute) {
            selectedItem = index
        }
    }

    NavigationBar {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.route) {
                NavigationItem.Conversions.Filters.route -> {}
                else -> {}
            }
        }

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = { Icon(painterResource(item.icon.iconRes), contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItem == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BottomNavIconSelectedColor,
                    selectedTextColor = TextDefault,
                    unselectedIconColor = BottomNavIconUnSelectedColor,
                    unselectedTextColor = BottomNavIconUnSelectedColor,
                    indicatorColor = LightPrimary
                ),
                onClick = {
                    selectedItem = index
                    currentRoute = item.route
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                when {
                                    route != NavigationItem.Conversions.route -> saveState = true
                                }
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
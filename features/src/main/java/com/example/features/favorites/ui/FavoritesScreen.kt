package com.example.features.favorites.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.features.common.theme.BackgroundDefault
import com.example.features.main.navigation.NavigationItem
import com.example.features.main.ui.ApplicationTopBar

@Composable
fun FavoritesRoute(viewModel: FavoritesViewModel = hiltViewModel()) {
    FavoritesScreen(viewModel)
}

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {
    val favorites = viewModel.favorites.collectAsState().value
    viewModel.getFavorites()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = BackgroundDefault),
    ) {

        ApplicationTopBar(NavigationItem.Favorites.route)

        LazyColumn(Modifier.fillMaxSize()) {
            items(
                items = favorites,
                itemContent = {

                    val favoriteItem = viewModel.favoriteItemRate.collectAsState()
                    val rate = if (favoriteItem.value.rates.isNotEmpty()) {
                        favoriteItem.value.rates.values.first().toString()
                    } else {
                        "Invalid"
                    }
                    val currencyCodes = it.conversionRate.split("/")
                    viewModel.getFavoriteItemRate(currencyCodes.first(), currencyCodes.last())

//                    val n = viewModel.getFavoriteItemRate(currencyCodes.first(), currencyCodes.last()).collectAsState(null)


                    ConversionRateItem(rate, it) { favorite ->
                        viewModel.removeFromFavorites(favorite = favorite)
                    }
                }
            )
        }
    }
}
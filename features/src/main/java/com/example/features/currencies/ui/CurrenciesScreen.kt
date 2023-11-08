package com.example.features.currencies.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.features.common.theme.BackgroundDefault
import com.example.features.common.theme.BackgroundHeader
import com.example.features.common.theme.Secondary
import com.example.domain.entity.Favorite
import com.example.domain.entity.Rate
import com.example.features.R
import com.example.features.common.filter.filter
import com.example.features.main.navigation.NavigationItem
import com.example.features.main.ui.ApplicationTopBar


@Composable
fun CurrenciesRoute(
    entry: NavBackStackEntry,
    navController: NavController,
    viewModel: CurrenciesViewModel = hiltViewModel()
) {
    CurrenciesScreen(entry, viewModel) {
        navController.navigate(NavigationItem.Conversions.Filters.route)
    }
}

@Composable
fun CurrenciesScreen(entry: NavBackStackEntry, viewModel: CurrenciesViewModel, onFilterClick: () -> Unit) {
    val defaultBase: MutableState<String> = remember { mutableStateOf("USD") }
    viewModel.getCurrencies(defaultBase.value)
    val currencies = viewModel.currencies.collectAsState().value
    val rates = currencies.rates.map {
            val isFavoriteItem = viewModel.isFavorite(defaultBase.value, it.key)
            Rate(code = it.key, value = it.value, isFavorite = isFavoriteItem)
        }

    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val selectedFilter = entry.savedStateHandle.get<String>("selected_filter")

    val painter = if (expanded)
        painterResource(R.drawable.ic_arrow_up)
    else
        painterResource(R.drawable.ic_arrow_down)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = BackgroundDefault),
    ) {

        ApplicationTopBar(NavigationItem.Conversions.Currencies.route)
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(color = BackgroundHeader)
                .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.Top,
        ) {
            OutlinedTextField(
                value = defaultBase.value,
                onValueChange = { defaultBase.value = it },
                shape = RoundedCornerShape(size = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Secondary,
                    unfocusedBorderColor = Secondary
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(color = BackgroundDefault)
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textFieldSize = coordinates.size.toSize()
                    },
                trailingIcon = {
                    Image(
                        modifier = Modifier
                            .clickable { expanded = !expanded }
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp),
                        painter = painter,
                        contentDescription = "Currency codes Icon",
                        contentScale = ContentScale.None
                    )
                }
            )
            OutlinedButton(
                onClick = { onFilterClick.invoke() },
                border = BorderStroke(1.dp, Secondary),
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
                    .background(color = BackgroundDefault, shape = RoundedCornerShape(size = 8.dp)),
                content = {
                    Image(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "Filter Icon",
                        contentScale = ContentScale.None
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color(0xFF9DACDC)
                )
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = !expanded },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
            ) {
                rates.forEach { rate ->
                    DropdownMenuItem(
                        onClick = {
                            defaultBase.value = rate.code
                            viewModel.getCurrencies(defaultBase.value)
                            expanded = false
                        }
                    ) {
                        Text(text = rate.code)
                    }
                }
            }
        }
        LazyColumn(Modifier.fillMaxSize()) {
            items(
                items = rates.filter(selectedFilter),
                itemContent = {
                    RateItem(it) { rateItem ->
                        if (rateItem.isFavorite) {
                            viewModel.removeFromFavorites(favorite = Favorite(conversionRate = "${defaultBase.value}/${it.code}"))
                        } else {
                            Favorite(
                                conversionRate = "${defaultBase.value}/${rateItem.code}"
                            ).also { favorite ->
                                viewModel.addToFavorites(favorite)
                            }
                        }
                    }
                }
            )
        }
    }
}
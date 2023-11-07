package com.example.features.filters.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.common.theme.*
import com.example.features.main.navigation.NavigationItem
import com.example.features.main.ui.ApplicationTopBar

@Preview
@Composable
fun FiltersScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = BackgroundDefault),
    ) {

        ApplicationTopBar(NavigationItem.Conversions.Filters.route, true, navController)
        FilterRadioButtons()
    }
}

@Preview
@Composable
fun FilterRadioButtons() {
    val filters = mapOf("az" to "Code A-Z", "za" to "Code Z-A", "asc" to "Quote Asc.", "desc" to "Quote Desc.")
    val selectedOption = remember { mutableStateOf(filters.values.first()) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(16.dp),
    ) {

        Text(
            modifier = Modifier,
            text = "SORTED BY",
            fontSize = 12.sp,
            color = TextSecondary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )

        filters.values.forEach {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    fontSize = 20.sp,
                    color = TextDefault,
                    textAlign = TextAlign.Start
                )
                RadioButton(
                    modifier = Modifier,
                    selected = selectedOption.value == it,
                    onClick = { selectedOption.value = it },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = Secondary
                    )
                )
            }
        }

        TextButton(
            onClick = {

            },
        ) {

        }

    }
}
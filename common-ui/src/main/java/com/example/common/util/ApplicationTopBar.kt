package com.example.common.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.common.theme.BackgroundHeader

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ApplicationTopBar(title: String, isBackEnabled: Boolean = false, navController: NavHostController) {
//
//    TopAppBar(
//        colors = TopAppBarDefaults.smallTopAppBarColors(
//            containerColor = BackgroundHeader,
//            titleContentColor = BackgroundHeader,
//        ),
//        title = {
//            Text(
//                modifier = Modifier,
//                text = title,
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold
//            )
//        },
//        navigationIcon = {
//            if (isBackEnabled) {
//                IconButton(onClick = {
//
//                }) {
//                    Icon(Icons.Filled.ArrowBack, "backIcon")
//                }
//            }
//        }
//    )
//}
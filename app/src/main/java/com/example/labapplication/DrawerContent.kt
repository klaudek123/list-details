package com.example.labapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DrawerContent(navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color(0xFFD7D2E4))
            .fillMaxHeight()
            .width(IntrinsicSize.Min)
    ) {
        Text(text = "Navigation", modifier = Modifier.padding(8.dp), fontSize = if (DeviceConfig.isTablet()) 32.sp else 16.sp)
        Divider()
        Text(
            text = "Home",
            fontWeight = FontWeight.Bold,
            fontSize = if (DeviceConfig.isTablet()) 40.sp else 20.sp,
            modifier = Modifier.padding(8.dp).clickable {
                navController.navigate(route = "main") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            fontSize = if (DeviceConfig.isTablet()) 40.sp else 20.sp,
            modifier = Modifier.padding(8.dp).clickable {
                navController.navigate(route = "category") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
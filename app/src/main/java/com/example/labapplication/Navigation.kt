package com.example.labapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navig(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "animation"
    ) {
        composable(route = "animation") {
            AnimationScreen(navController)
        }
        composable(route = "main") {
            Main(navController)
        }
        composable(route = "category") {
            Category(navController)
        }
        composable(route = "search") {
            SearchScreen(navController)
        }
        composable("trailDetails/{trailId}") { backStackEntry ->
            val trailId = backStackEntry.arguments?.getString("trailId")
            val selectedTrail = trails.find { it.id.toString() == trailId }
            selectedTrail?.let { TrailDetailsScreen(it) }
        }
    }
}
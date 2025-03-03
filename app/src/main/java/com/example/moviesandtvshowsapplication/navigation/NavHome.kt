package com.example.moviesandtvshowsapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesandtvshowsapplication.screens.DetailsScreen
import com.example.moviesandtvshowsapplication.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailsScreen(
    val id: Int,
)

@Serializable
object HomeScreen

@Composable
fun NavHome() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = HomeScreen) {
        composable<HomeScreen> { HomeScreen(navController = navController) }
        composable<DetailsScreen> { movieId ->
            val id = movieId.arguments?.getString("id")
            DetailsScreen(navController = navController, id = id!!.toInt())
        }
    }
}
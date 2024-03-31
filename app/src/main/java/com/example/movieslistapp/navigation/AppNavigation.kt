package com.example.movieslistapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieslistapp.features.move.presentation.view.MovieDetailsScreen
import com.example.movieslistapp.features.move.presentation.view.PopularMoviesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.PopularMoviesScreen.route) {
        composable(AppScreens.PopularMoviesScreen.route) { backStackEntry ->
            PopularMoviesScreen(
                navController = navController,
            )
        }
        composable("${AppScreens.MovieDetailsScreen.route}/{movieId}") { backStackEntry ->
            // Extract movieId from the route
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                // If movieId is not null, navigate to MovieDetailsScreen
                MovieDetailsScreen(
                    navController = navController,
                    movieId = movieId
                )
            }
        }
    }
}
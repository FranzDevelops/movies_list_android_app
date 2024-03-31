package com.example.movieslistapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieslistapp.di.FirebaseModule
import com.example.movieslistapp.features.authentication.AuthenticationScreen
import com.example.movieslistapp.features.move.presentation.view.MovieDetailsScreen
import com.example.movieslistapp.features.move.presentation.view.PopularMoviesScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val firebaseAuth = FirebaseModule.providerFirebaseAuthInstance()

    // Observe Firebase Auth state changes
    LaunchedEffect(key1 = Unit) {
        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                // User is logged in, navigate to MoviesView
                navController.navigate(AppScreens.PopularMoviesScreen.route) {
                    // Remove all entries from the back stack to prevent going back to the AuthView
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    // Avoid multiple copies of the same destination when re-logging in
                    launchSingleTop = true
                }
            } else {
                // User is logged out, navigate to AuthView
                navController.navigate(AppScreens.AuthScreen.route) {
                    // Remove all entries from the back stack
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    // Avoid multiple copies of the same destination
                    launchSingleTop = true
                }
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
    val startDestination = if (firebaseAuth.currentUser != null) AppScreens.PopularMoviesScreen.route else AppScreens.AuthScreen.route
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppScreens.AuthScreen.route) {
            AuthenticationScreen(navController = navController)
        }
        composable(AppScreens.PopularMoviesScreen.route) { backStackEntry ->
            PopularMoviesScreen(navController = navController)
        }
        composable("${AppScreens.MovieDetailsScreen.route}/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                MovieDetailsScreen(
                    navController = navController,
                    movieId = movieId
                )
            }
        }
    }
}
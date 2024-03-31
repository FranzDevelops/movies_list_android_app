package com.example.movieslistapp.navigation

sealed class AppScreens(val route: String) {
    object AuthScreen: AppScreens("authentication")
    object PopularMoviesScreen: AppScreens("movies")
    object MovieDetailsScreen: AppScreens("details")
}
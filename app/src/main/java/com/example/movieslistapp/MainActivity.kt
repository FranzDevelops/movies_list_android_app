package com.example.movieslistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieslistapp.features.move.presentation.view.PopularMoviesScreen
import com.example.movieslistapp.navigation.AppNavigation
import com.example.movieslistapp.ui.theme.MoviesListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesListAppTheme {
                AppNavigation()
            }
        }
    }
}
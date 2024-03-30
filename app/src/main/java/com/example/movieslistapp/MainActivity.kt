package com.example.movieslistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.movieslistapp.features.movelist.presentation.view.PopularMoviesScreen
import com.example.movieslistapp.ui.theme.MoviesListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesListAppTheme {
                PopularMoviesScreen()
            }
        }
    }
}
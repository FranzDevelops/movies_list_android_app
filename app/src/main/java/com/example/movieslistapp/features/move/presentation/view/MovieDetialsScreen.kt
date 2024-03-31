package com.example.movieslistapp.features.move.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.movieslistapp.features.move.domain.model.PopularMovie
import com.example.movieslistapp.features.move.presentation.viewmodel.PopularMoviesViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movieslistapp.features.move.domain.model.MovieDetails
import com.example.movieslistapp.features.move.presentation.viewmodel.MovieDetailsViewModel

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController,
    movieId: Int
) {
    val movie: MovieDetails by viewModel.movieDetails.observeAsState(initial = MovieDetails())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(true) {
        viewModel.onCreate(movieId = movieId)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading && movie != null) {
                CircularProgressIndicator()
            } else {
                Text(text = movie.title ?: "No title")
            }
        }
    }
}
package com.example.movieslistapp.features.movelist.presentation.view

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
import com.example.movieslistapp.features.movelist.domain.model.PopularMovie
import com.example.movieslistapp.features.movelist.presentation.viewmodel.PopularMoviesViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PopularMoviesScreen(viewModel: PopularMoviesViewModel = hiltViewModel()) {
    val movies: List<PopularMovie> by viewModel.popularMoviesList.observeAsState(initial = emptyList())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(true) {
        viewModel.onCreate()
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
            if (isLoading && movies.isEmpty()) {
                CircularProgressIndicator()
            } else {
                Text(text = movies.toString())
            }
        }
    }
}
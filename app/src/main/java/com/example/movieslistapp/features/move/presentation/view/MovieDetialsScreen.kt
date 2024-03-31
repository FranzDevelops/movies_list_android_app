package com.example.movieslistapp.features.move.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieslistapp.features.move.domain.model.MovieDetails
import com.example.movieslistapp.features.move.presentation.viewmodel.MovieDetailsViewModel
import com.example.movieslistapp.utils.Constants

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
        if (isLoading && movie != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        ImageRequest.Builder(LocalContext.current)
                            .data(Constants.IMAGE_URL + movie.posterPath)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Movie Poster",
                        contentScale = ContentScale.FillHeight
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.7f)) // Faded opaque black overlay
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            text = movie.title ?: "No title",
                            style = MaterialTheme.typography.displayMedium,
                            color = Color.White // White text for better contrast
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        // Genres
                        Row {
                            Text(
                                text = "Genres: ",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White // White text for better contrast
                            )
                            movie.genres?.forEachIndexed { index, genre ->
                                Text(
                                    text = genre.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Overview: " + movie.overview,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                    }
                }
            }

        }
    }
}
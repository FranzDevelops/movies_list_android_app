package com.example.movieslistapp.features.move.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieslistapp.di.FirebaseModule
import com.example.movieslistapp.navigation.AppScreens
import com.example.movieslistapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth


private fun navigateToDetails(movieId: Int, navController: NavController) {
    navController.navigate("details_screen_route/$movieId")
}
@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel(),
    navController: NavController
) {
    val firebaseAuth = FirebaseModule.providerFirebaseAuthInstance()

    val movies: List<PopularMovie> by viewModel.popularMoviesList.observeAsState(initial = emptyList())
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)

    LaunchedEffect(true) {
        viewModel.onCreate()
    }

    Surface(
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
                MoviesList(
                    movies,
                    onLoadMore = { viewModel.loadMoreMovies() },
                    navController
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = {
                    // Logout when the button is clicked
                    firebaseAuth.signOut()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Default.Lock, contentDescription = "Logout")
            }
        }
    }
}

@Composable
fun MoviesList(movies: List<PopularMovie>, onLoadMore: () -> Unit, navController: NavController) {
    val lazyListState = rememberLazyGridState()

    LazyVerticalGrid(
        state = lazyListState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize()
    ) {
        items(movies.size) { index ->
            MovieItem(movie = movies[index], navController)
            if (index == movies.size - 2) {
                onLoadMore()
            }
        }
    }
}

@Composable
fun MovieItem(movie: PopularMovie, navController: NavController) {
    Box(
        modifier = Modifier
            .clickable { navController.navigate("${AppScreens.MovieDetailsScreen.route}/${movie.id}") }
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimary)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                ImageRequest.Builder(LocalContext.current)
                    .data(Constants.IMAGE_URL + movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = "Movie Poster",
                contentScale = ContentScale.FillWidth
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .height(30.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black,
                    maxLines = 1, // Limiting the text to a single line
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = movie.releaseDate,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}
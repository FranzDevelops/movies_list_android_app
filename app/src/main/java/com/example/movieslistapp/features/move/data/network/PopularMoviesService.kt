package com.example.movieslistapp.features.move.data.network

import com.example.movieslistapp.features.move.data.model.MovieDetailsResponse
import com.example.movieslistapp.features.move.data.model.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesService @Inject constructor(
    private val api: MoviesApiClient
) {
    suspend fun fetchPopularMovies(page: Int): PopularMoviesResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.fetchPopularMovies(page = page)
            response.body()
        }
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.getMovieDetails(movieId)
            response.body()
        }
    }
}
package com.example.movieslistapp.features.movelist.data.network

import com.example.movieslistapp.features.movelist.data.model.PopularMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PopularMoviesService @Inject constructor(
    private val api: PopularMoviesApiClient
) {
    suspend fun fetchPopularMovies(): PopularMoviesResponse? {
        return withContext(Dispatchers.IO) {
            val response = api.fetchPopularMovies()
            response.body()
        }
    }
}
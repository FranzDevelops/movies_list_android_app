package com.example.movieslistapp.features.movelist.data

import com.example.movieslistapp.features.movelist.data.model.PopularMoviesResponse
import com.example.movieslistapp.features.movelist.data.network.PopularMoviesService
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val api: PopularMoviesService
) {
    suspend fun fetchPopularMovies(): PopularMoviesResponse? {
        val response: PopularMoviesResponse? = api.fetchPopularMovies()
        return response
    }
}
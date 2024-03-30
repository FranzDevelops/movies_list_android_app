package com.example.movieslistapp.features.movelist.data

import com.example.movieslistapp.features.movelist.data.model.PopularMoviesResponse
import com.example.movieslistapp.features.movelist.data.network.PopularMoviesService
import com.example.movieslistapp.features.movelist.domain.model.PopularMoviesList
import com.example.movieslistapp.features.movelist.domain.model.toDomain
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(
    private val api: PopularMoviesService
) {
    suspend fun fetchPopularMovies(): List<PopularMoviesList> {
        val response: PopularMoviesResponse? = api.fetchPopularMovies()
        if (response?.results?.isNotEmpty() == true) {
            return response.results.map { it.toDomain() }
        }
        return emptyList()
    }
}
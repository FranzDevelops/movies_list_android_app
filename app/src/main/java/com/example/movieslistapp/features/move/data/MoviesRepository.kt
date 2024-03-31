package com.example.movieslistapp.features.move.data

import com.example.movieslistapp.features.move.data.model.MovieDetailsResponse
import com.example.movieslistapp.features.move.data.model.PopularMoviesResponse
import com.example.movieslistapp.features.move.data.network.PopularMoviesService
import com.example.movieslistapp.features.move.domain.model.MovieDetails
import com.example.movieslistapp.features.move.domain.model.PopularMovie
import com.example.movieslistapp.features.move.domain.model.toDomainDetails
import com.example.movieslistapp.features.move.domain.model.toDomainMovies
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: PopularMoviesService
) {
    suspend fun fetchPopularMovies(page: Int): List<PopularMovie> {
        val response: PopularMoviesResponse? = api.fetchPopularMovies(page)
        if (response?.results?.isNotEmpty() == true) {
            return response.results.map { it.toDomainMovies() }
        }
        return emptyList()
    }

    suspend fun getMovieDetails(movieId: Int): MovieDetails? {
        val response: MovieDetailsResponse? = api.getMovieDetails(movieId)
        return response?.let { it.toDomainDetails() }
    }
}
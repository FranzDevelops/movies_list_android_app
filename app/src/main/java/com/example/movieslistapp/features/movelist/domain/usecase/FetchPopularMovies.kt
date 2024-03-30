package com.example.movieslistapp.features.movelist.domain.usecase

import com.example.movieslistapp.features.movelist.data.PopularMoviesRepository
import com.example.movieslistapp.features.movelist.domain.model.PopularMoviesList
import javax.inject.Inject

class FetchPopularMovies @Inject constructor(
    private val repository: PopularMoviesRepository
){
        suspend operator fun invoke():List<PopularMoviesList>{
            val movies = repository.fetchPopularMovies()
            return movies
        }
}
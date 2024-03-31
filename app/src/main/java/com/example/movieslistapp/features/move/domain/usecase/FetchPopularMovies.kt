package com.example.movieslistapp.features.move.domain.usecase

import com.example.movieslistapp.features.move.data.MoviesRepository
import com.example.movieslistapp.features.move.domain.model.PopularMovie
import javax.inject.Inject

class FetchPopularMovies @Inject constructor(
    private val repository: MoviesRepository
){
        suspend operator fun invoke(page: Int):List<PopularMovie>{
            val movies = repository.fetchPopularMovies(page)
            return movies
        }
}
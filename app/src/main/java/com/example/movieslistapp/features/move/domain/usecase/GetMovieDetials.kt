package com.example.movieslistapp.features.move.domain.usecase

import com.example.movieslistapp.features.move.data.MoviesRepository
import com.example.movieslistapp.features.move.domain.model.MovieDetails
import javax.inject.Inject

class GetMovieDetials @Inject constructor(
    private val repository: MoviesRepository
){
        suspend operator fun invoke(movieId: Int): MovieDetails? {
            val details = repository.getMovieDetails(movieId)
            return details
        }
}
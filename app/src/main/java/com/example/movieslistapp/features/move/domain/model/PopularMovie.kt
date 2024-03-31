package com.example.movieslistapp.features.move.domain.model

import com.example.movieslistapp.features.move.data.model.PopularMoviesResponse

data class PopularMovie(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String
)

fun PopularMoviesResponse.Movie.toDomainMovies() =
    PopularMovie(id, title, overview, releaseDate, posterPath)
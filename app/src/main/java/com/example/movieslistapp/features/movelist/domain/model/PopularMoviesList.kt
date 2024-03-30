package com.example.movieslistapp.features.movelist.domain.model

import com.example.movieslistapp.features.movelist.data.model.PopularMoviesResponse

data class PopularMoviesList(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String
)

fun PopularMoviesResponse.Movie.toDomain() =
    PopularMoviesList(id, title, overview, releaseDate, posterPath)
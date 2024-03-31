package com.example.movieslistapp.features.move.domain.model

import com.example.movieslistapp.features.move.data.model.MovieDetailsResponse

data class MovieDetails (
    val id: Int?,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val genres: List<MovieDetailsResponse.Genre>?,
    val runtime: Int?,
    val budget: Int?,
    val revenue: Int?
) {
    constructor() : this(
        id = null,
        title = null,
        overview = null,
        posterPath = null,
        releaseDate = null,
        genres = null,
        runtime = null,
        budget = null,
        revenue = null
    )
}

fun MovieDetailsResponse.toDomainDetails() =
    MovieDetails(id, title, overview, posterPath, releaseDate, genres, runtime, budget, revenue)

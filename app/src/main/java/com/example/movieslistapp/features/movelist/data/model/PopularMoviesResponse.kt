package com.example.movieslistapp.features.movelist.data.model

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
) {
    data class Movie(
        val id: Int,
        @SerializedName("poster_path") val posterPath: String,
        val title: String,
        val overview: String,
        @SerializedName("release_date") val releaseDate: String
    )
}

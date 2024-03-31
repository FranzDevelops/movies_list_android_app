package com.example.movieslistapp.features.move.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("poster_path") val posterPath: String,
    val title: String,
    val overview: String,
    val genres: List<Genre>,
    @SerializedName("release_date") val releaseDate: String,
    val runtime: Int,
    val id: Int,
    val budget: Int,
    val revenue: Int
) {
    data class Genre(
        val name: String
    )
}

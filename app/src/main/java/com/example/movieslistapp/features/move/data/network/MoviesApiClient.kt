package com.example.movieslistapp.features.move.data.network

import com.example.movieslistapp.features.move.data.model.MovieDetailsResponse
import com.example.movieslistapp.features.move.data.model.PopularMoviesResponse
import com.example.movieslistapp.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiClient {
    @GET("discover/movie")
    suspend fun fetchPopularMovies(
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("include_video") includeVide: Boolean = true,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Header("Authorization") bearerToken: String = "Bearer " + Constants.API_KEY
    ): Response<PopularMoviesResponse>

    @GET("movie/{movieId}") // Define the dynamic path variable inside curly braces
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Header("Authorization") bearerToken: String = "Bearer " + Constants.API_KEY
    ): Response<MovieDetailsResponse>

}
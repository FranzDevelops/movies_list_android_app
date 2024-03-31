package com.example.movieslistapp.features.move.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieslistapp.features.move.domain.model.MovieDetails
import com.example.movieslistapp.features.move.domain.usecase.GetMovieDetials
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetials: GetMovieDetials
) : ViewModel() {
    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onCreate(movieId: Int) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = getMovieDetials(movieId)

            result?.let { movieDetails ->
                _movieDetails.postValue(movieDetails)
                _isLoading.postValue(false)
            }
        }
    }
}
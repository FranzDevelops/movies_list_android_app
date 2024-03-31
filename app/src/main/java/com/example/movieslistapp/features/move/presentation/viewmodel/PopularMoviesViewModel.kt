package com.example.movieslistapp.features.move.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieslistapp.features.move.domain.model.PopularMovie
import com.example.movieslistapp.features.move.domain.usecase.FetchPopularMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val fetchPopularMovies: FetchPopularMovies
) : ViewModel() {
    private val _popularMovieList = MutableLiveData<List<PopularMovie>>()
    val popularMoviesList: LiveData<List<PopularMovie>> = _popularMovieList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onCreate(page: Int = 1) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = fetchPopularMovies(page)

            if (!result.isNullOrEmpty()) {
                _popularMovieList.postValue(result)
                _isLoading.postValue(false)
            }
        }
    }
}
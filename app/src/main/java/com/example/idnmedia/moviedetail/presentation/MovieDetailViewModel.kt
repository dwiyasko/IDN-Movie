package com.example.idnmedia.moviedetail.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import com.example.idnmedia.moviedetail.domain.usecase.GetMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetail: GetMovieDetail
) : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetail>()
    val movieDetail: LiveData<MovieDetail> = _movieDetail

    fun loadMovieDetail(id: Long) {
        getMovieDetail.execute(
            onScope = viewModelScope,
            movieId = id,
            onSuccess = { _movieDetail.value = it },
            onError = { Log.wtf("ERROR", it.message) }
        )
    }
}

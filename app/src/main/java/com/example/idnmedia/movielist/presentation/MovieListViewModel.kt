package com.example.idnmedia.movielist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.idnmedia.movielist.domain.usecase.GetTopRatedMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getTopRatedMovies: GetTopRatedMovies,
) : ViewModel() {

    fun loadTopRatedMovies() = Pager(PagingConfig(pageSize = 20)) {
        MoviesPagingSource(getTopRatedMovies)
    }.flow.cachedIn(viewModelScope)
}

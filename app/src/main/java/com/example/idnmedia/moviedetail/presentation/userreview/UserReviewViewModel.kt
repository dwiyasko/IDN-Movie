package com.example.idnmedia.moviedetail.presentation.userreview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.idnmedia.moviedetail.domain.usecase.GetAllReviews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserReviewViewModel @Inject constructor(
    private val getAllReviews: GetAllReviews,
) : ViewModel() {

    fun loadReviews(movieId: Long) = Pager(PagingConfig(pageSize = 20)) {
        UserReviewPagingSource(getAllReviews, movieId)
    }.flow.cachedIn(viewModelScope)
}

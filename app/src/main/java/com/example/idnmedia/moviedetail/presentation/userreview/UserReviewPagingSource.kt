package com.example.idnmedia.moviedetail.presentation.userreview

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.idnmedia.moviedetail.domain.model.Review
import com.example.idnmedia.moviedetail.domain.usecase.GetAllReviews

class UserReviewPagingSource(
    private val getAllReviews: GetAllReviews,
    private val movieId: Long,
) : PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val currentPage =
            params.key?.let { getAllReviews.execute(movieId, it) } ?: getAllReviews.execute(
                movieId = movieId,
                page = 1
            )

        val nextKey =
            if (currentPage.reviews.isEmpty() || currentPage.page == currentPage.totalPage) null
            else (params.key ?: 0) + 1

        return LoadResult.Page(
            data = currentPage.reviews,
            prevKey = null,
            nextKey = nextKey,
        )
    }
}
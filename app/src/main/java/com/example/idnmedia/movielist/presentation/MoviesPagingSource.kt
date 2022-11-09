package com.example.idnmedia.movielist.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.idnmedia.movielist.domain.model.Movie
import com.example.idnmedia.movielist.domain.usecase.GetTopRatedMovies

class MoviesPagingSource(
    private val getTopRatedMovies: GetTopRatedMovies,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val currentPage =
            params.key?.let { getTopRatedMovies.execute(it) } ?: getTopRatedMovies.execute(page = 1)

        val nextKey =
            if (currentPage.content.isEmpty() || currentPage.page == currentPage.totalPages) null
            else (params.key ?: 1) + 1

        return LoadResult.Page(
            data = currentPage.content,
            prevKey = null,
            nextKey = nextKey,
        )
    }
}

package com.example.idnmedia.moviedetail.domain.repository

import com.example.idnmedia.moviedetail.domain.model.MovieClip
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import com.example.idnmedia.moviedetail.domain.model.MovieReviews

interface MovieDetailRepository {
    suspend fun getMovieDetail(id: Long): MovieDetail
    suspend fun getMovieReviews(id: Long, page: Int = 1): MovieReviews
    suspend fun getMovieClips(id: Long): List<MovieClip>
}

package com.example.idnmedia.moviedetail.domain.usecase

import com.example.idnmedia.moviedetail.domain.model.MovieReviews
import com.example.idnmedia.moviedetail.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetAllReviews @Inject constructor(private val repository: MovieDetailRepository) {
    suspend fun execute(movieId: Long, page: Int): MovieReviews {
        return repository.getMovieReviews(movieId, page)
    }
}

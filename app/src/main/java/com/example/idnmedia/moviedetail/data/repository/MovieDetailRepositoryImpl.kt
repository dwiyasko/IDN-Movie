package com.example.idnmedia.moviedetail.data.repository

import com.example.idnmedia.moviedetail.data.repository.MovieClipMapper.mapToDomainModel
import com.example.idnmedia.moviedetail.data.repository.MovieDetailMapper.toDomainModel
import com.example.idnmedia.moviedetail.data.repository.MovieReviewMapper.toDomainModel
import com.example.idnmedia.moviedetail.data.service.DetailApi
import com.example.idnmedia.moviedetail.domain.model.MovieClip
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import com.example.idnmedia.moviedetail.domain.model.MovieReviews
import com.example.idnmedia.moviedetail.domain.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val api: DetailApi,
) : MovieDetailRepository {

    override suspend fun getMovieDetail(id: Long): MovieDetail {
        return api.getMovieDetail(id).toDomainModel()
    }

    override suspend fun getMovieReviews(id: Long, page: Int): MovieReviews {
        return api.getUserReview(id, page).toDomainModel()
    }

    override suspend fun getMovieClips(id: Long): List<MovieClip> {
        return api.getMovieClips(id).results.mapToDomainModel()
    }
}

package com.example.idnmedia.moviedetail.data.service

import com.example.idnmedia.moviedetail.data.dto.MovieClipsResponse
import com.example.idnmedia.moviedetail.data.dto.MovieDetailResponse
import com.example.idnmedia.moviedetail.data.dto.MovieReviewResponse
import com.example.idnmedia.utils.Constant.REQUIRE_AUTH
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailApi {

    @GET("/3/movie/{id}")
    @Headers(REQUIRE_AUTH)
    suspend fun getMovieDetail(@Path("id") id: Long): MovieDetailResponse

    @GET("/3/movie/{id}/reviews")
    @Headers(REQUIRE_AUTH)
    suspend fun getUserReview(
        @Path("id") movieId: Long,
        @Query("page") page: Int = 1
    ): MovieReviewResponse

    @GET("/3/movie/{id}/videos")
    @Headers(REQUIRE_AUTH)
    suspend fun getMovieClips(@Path("id") movieId: Long): MovieClipsResponse
}

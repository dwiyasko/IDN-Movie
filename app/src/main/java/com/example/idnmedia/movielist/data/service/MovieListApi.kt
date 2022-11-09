package com.example.idnmedia.movielist.data.service

import com.example.idnmedia.movielist.data.dto.MoviesDto
import com.example.idnmedia.utils.Constant.REQUIRE_AUTH
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieListApi {

    @GET("/3/movie/top_rated")
    @Headers(REQUIRE_AUTH)
    suspend fun getTopRatedMovie(@Query("page") page: Int = 1): MoviesDto
}

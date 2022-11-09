package com.example.idnmedia.movielist.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesDto(
    val page: Int,
    val results: List<MovieDto>,

    @Json(name = "total_pages")
    val totalPage: Int,
)

@JsonClass(generateAdapter = true)
data class MovieDto(
    val id: Long,
    val title: String,

    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "vote_average")
    val voteAverage: Float,
)

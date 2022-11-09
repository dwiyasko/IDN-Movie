package com.example.idnmedia.moviedetail.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetailResponse(
    val id: Long,
    val title: String,
    val overview: String,
    val status: String,
    val tagline: String,
    val revenue: Long,
    val runtime: Int,
    val genres: List<MovieDetailGenre>,

    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Long,
    @Json(name = "original_title")
    val originalTitle: String,
    @Json(name = "homepage")
    val homepageUrl: String,
    @Json(name = "poster_path")
    val posterPath: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "backdrop_path")
    val backdropPath: String,
)

@JsonClass(generateAdapter = true)
data class MovieDetailGenre(
    val id: Int,
    val name: String,
)

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    val id: Long,
    val name: String,
    @Json(name = "logo_path")
    val logoPath: String,
)

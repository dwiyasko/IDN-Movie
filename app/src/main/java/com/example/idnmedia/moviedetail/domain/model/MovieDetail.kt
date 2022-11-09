package com.example.idnmedia.moviedetail.domain.model

import java.util.*

data class MovieDetail(
    val id: Long,
    val title: String,
    val overview: String,
    val status: String,
    val tagline: String,
    val revenue: Long,
    val runtime: Int,
    val genres: List<Genre>,
    val voteAverage: Double,
    val voteCount: Long,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: Date,
    val backdropPath: String,
    val trailerKey: String? = null,
    val reviews: MovieReviews? = null,
)

data class Genre(val name: String)

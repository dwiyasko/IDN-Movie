package com.example.idnmedia.moviedetail.domain.model

import java.util.*

data class MovieReviews(
    val page: Int,
    val totalPage: Int,
    val totalReview: Int,
    val reviews: List<Review>
)

data class Review(
    val id: String,
    val authorName: String,
    val authorAva: String?,
    val content: String,
    val createdAt: Date,
    val rating: Int,
)

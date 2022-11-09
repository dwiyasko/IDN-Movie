package com.example.idnmedia.moviedetail.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieReviewResponse(
    val page: Int,
    val results: List<ReviewDto>,

    @Json(name = "total_pages")
    val totalPages: Int,
    @Json(name = "total_results")
    val totalResults: Int,
)

@JsonClass(generateAdapter = true)
data class ReviewDto(
    val id: String,
    val content: String,

    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "author_details")
    val author: AuthorDto,
)

@JsonClass(generateAdapter = true)
data class AuthorDto(
    val name: String,
    val username: String,
    @Json(name = "avatar_path")
    val avatarPath: String?,
    val rating: Double? = 0.0,
)

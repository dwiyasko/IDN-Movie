package com.example.idnmedia.movielist.domain.model

data class Movies(
    val page: Int,
    val totalPages: Int,
    val content: List<Movie>
)

data class Movie(
    val id: Long,
    val title: String,
    val posterPath: String,
    val rating: Float,
)

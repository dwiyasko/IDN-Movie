package com.example.idnmedia.movielist.domain.repository

import com.example.idnmedia.movielist.domain.model.Movies

interface MainRepository {
    suspend fun getTopRatedMovies(page: Int): Movies
}

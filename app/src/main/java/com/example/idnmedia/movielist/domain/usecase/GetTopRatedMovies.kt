package com.example.idnmedia.movielist.domain.usecase

import com.example.idnmedia.movielist.domain.model.Movies
import com.example.idnmedia.movielist.domain.repository.MainRepository
import javax.inject.Inject

class GetTopRatedMovies @Inject constructor(private val repository: MainRepository) {

    suspend fun execute(page: Int): Movies {
        return repository.getTopRatedMovies(page)
    }
}

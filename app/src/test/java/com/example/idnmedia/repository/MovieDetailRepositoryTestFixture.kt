package com.example.idnmedia.repository

import com.example.idnmedia.moviedetail.data.repository.MovieDetailRepositoryImpl
import com.example.idnmedia.moviedetail.data.service.DetailApi
import com.example.idnmedia.moviedetail.domain.repository.MovieDetailRepository
import org.mockito.kotlin.mock

open class MovieDetailRepositoryTestFixture {

    val api: DetailApi = mock()
    val repository: MovieDetailRepository = MovieDetailRepositoryImpl(api)
}
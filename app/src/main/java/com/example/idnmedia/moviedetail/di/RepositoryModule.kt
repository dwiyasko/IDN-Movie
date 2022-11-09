package com.example.idnmedia.moviedetail.di

import com.example.idnmedia.moviedetail.data.repository.MovieDetailRepositoryImpl
import com.example.idnmedia.moviedetail.domain.repository.MovieDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMovieDetailRepository(impl: MovieDetailRepositoryImpl): MovieDetailRepository
}

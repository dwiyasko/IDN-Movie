package com.example.idnmedia.movielist.di

import com.example.idnmedia.movielist.data.repository.MainRepositoryImpl
import com.example.idnmedia.movielist.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository
}

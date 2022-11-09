package com.example.idnmedia.movielist.data

import com.example.idnmedia.movielist.data.service.MovieListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideMovieListApi(retrofit: Retrofit): MovieListApi {
        return retrofit.create(MovieListApi::class.java)
    }
}

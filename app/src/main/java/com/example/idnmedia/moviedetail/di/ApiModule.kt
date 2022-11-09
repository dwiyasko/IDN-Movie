package com.example.idnmedia.moviedetail.di

import com.example.idnmedia.moviedetail.data.service.DetailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun provideDetailApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }
}

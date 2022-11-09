package com.example.idnmedia.movielist.data.repository

import com.example.idnmedia.movielist.data.dto.MovieDto
import com.example.idnmedia.movielist.data.dto.MoviesDto
import com.example.idnmedia.movielist.data.service.MovieListApi
import com.example.idnmedia.movielist.domain.model.Movie
import com.example.idnmedia.movielist.domain.model.Movies
import com.example.idnmedia.movielist.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MovieListApi
) : MainRepository {
    override suspend fun getTopRatedMovies(page: Int): Movies {
        return api.getTopRatedMovie(page).toDomain()
    }

    private fun MoviesDto.toDomain(): Movies {
        return Movies(
            page = page,
            totalPages = totalPage,
            content = results.mapToDomainModel()
        )
    }

    private fun List<MovieDto>.mapToDomainModel(): List<Movie> {
        return map { Movie(it.id, it.title, it.posterPath, it.voteAverage) }
    }
}

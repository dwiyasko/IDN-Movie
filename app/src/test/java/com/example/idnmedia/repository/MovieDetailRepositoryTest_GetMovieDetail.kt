package com.example.idnmedia.repository

import com.example.idnmedia.moviedetail.data.dto.MovieDetailResponse
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class MovieDetailRepositoryTest_GetMovieDetail : MovieDetailRepositoryTestFixture() {

    @Test
    fun `given api result is correct when getMovieDetail called should return domain model correctly`() {
        runBlocking {
            val apiResult = MovieDetailResponse(
                id = 10,
                title = "Movie 1",
                overview = "Film Bagus",
                status = "Released",
                tagline = "",
                revenue = 8_000_000,
                runtime = 64,
                genres = emptyList(),
                voteAverage = 1.5,
                voteCount = 1_000,
                originalTitle = "Original title",
                homepageUrl = "",
                posterPath = "",
                releaseDate = "2022-06-29",
                backdropPath = ""
            )
            whenever(api.getMovieDetail(10)).thenReturn(apiResult)

            val actualResult = repository.getMovieDetail(10)

            val expectedResult =
                MovieDetail(
                    id = 10,
                    title = "Movie 1",
                    overview = "Film Bagus",
                    status = "Released",
                    tagline = "",
                    revenue = 8_000_000,
                    runtime = 64,
                    genres = emptyList(),
                    voteAverage = 1.5,
                    voteCount = 1000,
                    originalTitle = "Original title",
                    posterPath = "",
                    backdropPath = "",
                    releaseDate = Calendar.getInstance().apply {
                        set(2022, 5, 29, 0, 0, 0)
                    }.time
                )

            verify(api).getMovieDetail(10)
            assertEquals(expectedResult.title, actualResult.title)
            assertEquals(expectedResult.genres, actualResult.genres)
            assertEquals(expectedResult.overview, actualResult.overview)
        }
    }
}

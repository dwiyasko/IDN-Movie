package com.example.idnmedia.repository

import com.example.idnmedia.moviedetail.data.dto.ClipDto
import com.example.idnmedia.moviedetail.data.dto.MovieClipsResponse
import com.example.idnmedia.moviedetail.domain.model.ClipSite.Others
import com.example.idnmedia.moviedetail.domain.model.ClipSite.Youtube
import com.example.idnmedia.moviedetail.domain.model.ClipType.Clip
import com.example.idnmedia.moviedetail.domain.model.ClipType.Trailer
import com.example.idnmedia.moviedetail.domain.model.MovieClip
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieDetailRepositoryTest_GetMovieClip : MovieDetailRepositoryTestFixture() {

    @Test
    fun `given api result is correct when getMovieClip called should return domain model correctly`() {
        runBlocking {
            val apiResult = MovieClipsResponse(
                listOf(
                    ClipDto(
                        name = "Trailer 2",
                        type = "Trailer",
                        site = "Youtube",
                        key = "abcsd",
                        isOfficial = true,
                    ),
                    ClipDto(
                        name = "Trailer 5",
                        type = "Clip",
                        site = "TikTok",
                        key = "abcsd",
                        isOfficial = true,
                    ),
                )
            )
            whenever(api.getMovieClips(10)).thenReturn(apiResult)

            val actualResult = repository.getMovieClips(10)

            val expectedResult =
                listOf(
                    MovieClip(
                        name = "Trailer 2",
                        type = Trailer,
                        site = Youtube,
                        key = "abcsd",
                        isOfficial = true,
                    ),
                    MovieClip(
                        name = "Trailer 5",
                        type = Clip,
                        site = Others,
                        key = "abcsd",
                        isOfficial = true,
                    ),
                )

            verify(api).getMovieClips(10)
            assertEquals(expectedResult, actualResult)
        }
    }
}

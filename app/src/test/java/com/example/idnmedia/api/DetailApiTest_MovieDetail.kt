package com.example.idnmedia.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException

class DetailApiTest_MovieDetail : DetailApiTestFixture() {

    @Test
    fun `given response is success should return correct DTO Response`() {
        runBlocking {
            server.respondWithSuccess(successResponse)

            val actualDTO = api.getMovieDetail(10)

            assertTrue(server.isPathEquals("/movie/10"))
            assertTrue(actualDTO.title == "Minions: The Rise of Gru")
            assertTrue(actualDTO.status == "Released")
        }
    }

    @Test(expected = HttpException::class)
    fun `given request is failed should throw HTTP Exception`() {
        runBlocking {
            server.responseWithFailed(withResponseCode = 500, withResponseBody = "")

            api.getMovieDetail(10)
        }
    }

    companion object {
        val successResponse = """
            {
                "adult": false,
                "backdrop_path": "/nmGWzTLMXy9x7mKd8NKPLmHtWGa.jpg",
                "belongs_to_collection": {
                    "id": 544669,
                    "name": "Minions Collection",
                    "poster_path": "/lqU48HkuPDpbumwHk9syT7FbxpC.jpg",
                    "backdrop_path": "/62Qe28oi9PaK3P2ljDYUDTGAyST.jpg"
                },
                "budget": 85000000,
                "genres": [
                    {
                        "id": 16,
                        "name": "Animation"
                    },
                    {
                        "id": 10751,
                        "name": "Family"
                    }
                ],
                "homepage": "https://www.minionsmovie.com/",
                "id": 438148,
                "imdb_id": "tt5113044",
                "original_language": "en",
                "original_title": "Minions: The Rise of Gru",
                "overview": "A fanboy of a supervillain supergroup known as the Vicious 6, Gru hatches a plan to become evil enough to join them, with the backup of his followers, the Minions.",
                "popularity": 1136.827,
                "poster_path": "/wKiOkZTN9lUUUNZLmtnwubZYONg.jpg",
                "production_companies": [
                    {
                        "id": 33,
                        "logo_path": "/8lvHyhjr8oUKOOy2dKXoALWKdp0.png",
                        "name": "Universal Pictures",
                        "origin_country": "US"
                    },
                    {
                        "id": 6704,
                        "logo_path": "/fOG2oY4m1YuYTQh4bMqqZkmgOAI.png",
                        "name": "Illumination",
                        "origin_country": "US"
                    }
                ],
                "production_countries": [
                    {
                        "iso_3166_1": "US",
                        "name": "United States of America"
                    }
                ],
                "release_date": "2022-06-29",
                "revenue": 924000000,
                "runtime": 87,
                "spoken_languages": [
                    {
                        "english_name": "English",
                        "iso_639_1": "en",
                        "name": "English"
                    }
                ],
                "status": "Released",
                "tagline": "A villain will rise.",
                "title": "Minions: The Rise of Gru",
                "video": false,
                "vote_average": 7.585,
                "vote_count": 2108
            }
        """.trimIndent()
    }
}

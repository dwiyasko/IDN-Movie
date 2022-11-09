package com.example.idnmedia.api

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException

class DetailApiTest_MovieClip : DetailApiTestFixture() {

    @Test
    fun `given response is success should return correct DTO Response`() {
        runBlocking {
            server.respondWithSuccess(successResponse)

            val actualDTO = api.getMovieClips(10)

            assertTrue(server.isPathEquals("/movie/10/videos"))
            assertTrue(actualDTO.results.size == 6)
            assertTrue(actualDTO.results[0].name == "I am Pretty Despicable! Extended Preview")
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
                "id": 438148,
                "results": [
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "I am Pretty Despicable! Extended Preview",
                        "key": "-ExA_Ojo1kY",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Clip",
                        "official": true,
                        "published_at": "2022-08-30T16:00:00.000Z",
                        "id": "630e8b051d3563007a18f038"
                    },
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "Yours to Own",
                        "key": "ssb0TrPogr8",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Teaser",
                        "official": true,
                        "published_at": "2022-08-24T17:25:31.000Z",
                        "id": "630cb576ede1b00083c3b896"
                    },
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "Official Trailer 3",
                        "key": "HhIl_XJ-OGA",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Trailer",
                        "official": true,
                        "published_at": "2022-06-06T15:00:15.000Z",
                        "id": "629e8340caa50837e3b31cfd"
                    },
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "Official Trailer 2",
                        "key": "6DxjJzmYsXo",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Trailer",
                        "official": true,
                        "published_at": "2022-03-30T15:00:03.000Z",
                        "id": "624487897caa4700476aac08"
                    },
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "Official Trailer",
                        "key": "OCSnxXtjFL0",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Trailer",
                        "official": true,
                        "published_at": "2020-02-05T16:00:04.000Z",
                        "id": "5e7675312fe2fa001214920f"
                    },
                    {
                        "iso_639_1": "en",
                        "iso_3166_1": "US",
                        "name": "Get Ready",
                        "key": "3Zibb6lVCRw",
                        "site": "YouTube",
                        "size": 1080,
                        "type": "Teaser",
                        "official": true,
                        "published_at": "2020-02-03T00:58:08.000Z",
                        "id": "60da15b017c443002d3df034"
                    }
                ]
            }
        """.trimIndent()
    }
}

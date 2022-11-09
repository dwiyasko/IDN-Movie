package com.example.idnmedia.api

import com.example.idnmedia.IDNMockWebServerTestRule
import com.example.idnmedia.moviedetail.data.service.DetailApi
import org.junit.Rule

open class DetailApiTestFixture {

    @get:Rule
    val server = IDNMockWebServerTestRule()

    val api = server.createApi(DetailApi::class.java)
}
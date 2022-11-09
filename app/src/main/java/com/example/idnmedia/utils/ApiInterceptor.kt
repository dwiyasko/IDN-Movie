package com.example.idnmedia.utils

import com.example.idnmedia.BuildConfig
import com.example.idnmedia.utils.Constant.REQUIRE_AUTH_KEY
import okhttp3.Interceptor
import okhttp3.Request

class ApiInterceptor {

    fun getInterceptor() = Interceptor { chain ->
        if (chain.request().isRequireAuthentication()) {
            chain.request().addApiKey()
        } else {
            chain.request()
        }.let {
            chain.proceed(it)
        }
    }

    private fun Request.isRequireAuthentication(): Boolean {
        return headers[REQUIRE_AUTH_KEY] != null
    }

    private fun Request.addApiKey(): Request {
        val newUrl = url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY).build()

        return newBuilder()
            .removeHeader(REQUIRE_AUTH_KEY)
            .url(newUrl)
            .build()
    }
}

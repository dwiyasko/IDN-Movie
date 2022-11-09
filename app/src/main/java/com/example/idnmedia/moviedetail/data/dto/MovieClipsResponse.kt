package com.example.idnmedia.moviedetail.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieClipsResponse(
    val results: List<ClipDto>,
)

@JsonClass(generateAdapter = true)
data class ClipDto(
    val name: String,
    val type: String,
    val site: String,
    val key: String,
    
    @Json(name = "official")
    val isOfficial: Boolean,
)

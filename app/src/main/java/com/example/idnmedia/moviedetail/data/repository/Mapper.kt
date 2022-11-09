package com.example.idnmedia.moviedetail.data.repository

import com.example.idnmedia.moviedetail.data.dto.*
import com.example.idnmedia.moviedetail.domain.model.*
import com.example.idnmedia.utils.DateUtils.DateFormat
import com.example.idnmedia.utils.DateUtils.convertStringToDate
import java.util.*

object MovieDetailMapper {
    fun MovieDetailResponse.toDomainModel(): MovieDetail {
        return MovieDetail(
            id = id,
            title = title,
            overview = overview,
            status = status,
            tagline = tagline,
            revenue = revenue,
            runtime = runtime,
            genres = genres.mapToDomainModel(),
            voteAverage = voteAverage,
            voteCount = voteCount,
            originalTitle = originalTitle,
            posterPath = posterPath,
            releaseDate = convertStringToDate(
                releaseDate,
                DateFormat.Simple
            ) ?: Calendar.getInstance().time,
            backdropPath = backdropPath,
        )
    }

    fun List<MovieDetailGenre>.mapToDomainModel(): List<Genre> {
        return map { Genre(it.name) }
    }
}

object MovieReviewMapper {
    fun MovieReviewResponse.toDomainModel(): MovieReviews {
        return MovieReviews(
            page = page,
            totalPage = totalPages,
            totalReview = totalResults,
            reviews = results.mapToDomainModel()
        )
    }

    fun List<ReviewDto>.mapToDomainModel(): List<Review> {
        return map {
            Review(
                id = it.id,
                authorName = it.author.name,
                authorAva = it.author.avatarPath,
                content = it.content,
                createdAt = convertStringToDate(
                    it.createdAt,
                    DateFormat.Complete
                ) ?: Calendar.getInstance().time,
                rating = it.author.rating?.toInt() ?: 0
            )
        }
    }
}

object MovieClipMapper {
    fun List<ClipDto>.mapToDomainModel(): List<MovieClip> {
        return map {
            MovieClip(
                name = it.name,
                key = it.key,
                type = ClipType.findMatches(withString = it.type),
                site = ClipSite.findMatches(withString = it.site),
                isOfficial = it.isOfficial
            )
        }
    }
}
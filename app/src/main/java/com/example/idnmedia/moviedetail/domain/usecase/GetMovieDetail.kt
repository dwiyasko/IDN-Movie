package com.example.idnmedia.moviedetail.domain.usecase

import com.example.idnmedia.moviedetail.domain.model.ClipSite.Youtube
import com.example.idnmedia.moviedetail.domain.model.ClipType.Trailer
import com.example.idnmedia.moviedetail.domain.model.MovieClip
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import com.example.idnmedia.moviedetail.domain.repository.MovieDetailRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMovieDetail @Inject constructor(private val repository: MovieDetailRepository) {

    fun execute(
        onScope: CoroutineScope,
        movieId: Long,
        onSuccess: (MovieDetail) -> Unit,
        onError: (Exception) -> Unit
    ) {
        onScope.launch(Dispatchers.Main) {
            try {
                val movie = withContext(Dispatchers.IO) {
                    val detail = repository.getMovieDetail(movieId)
                    val reviews = repository.getMovieReviews(movieId)
                    val trailer = repository.getMovieClips(movieId).takeFirstYoutubeTrailer()

                    detail.copy(reviews = reviews, trailerKey = trailer)
                }
                onSuccess(movie)
            } catch (error: Exception) {
                onError(error)
            }
        }
    }

    private fun List<MovieClip>.takeFirstYoutubeTrailer(): String? {
        return find { clip ->
            clip.isOfficial && clip.site == Youtube && clip.type == Trailer
        }?.key
    }
}

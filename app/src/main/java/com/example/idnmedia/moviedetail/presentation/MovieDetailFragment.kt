package com.example.idnmedia.moviedetail.presentation

import android.graphics.Bitmap
import android.text.TextUtils
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idnmedia.R
import com.example.idnmedia.common.BaseFragment
import com.example.idnmedia.databinding.FragmentMovieDetailBinding
import com.example.idnmedia.moviedetail.domain.model.Genre
import com.example.idnmedia.moviedetail.domain.model.MovieDetail
import com.example.idnmedia.moviedetail.domain.model.MovieReviews
import com.example.idnmedia.moviedetail.presentation.MovieDetailFragmentDirections.gotoReviewScreen
import com.example.idnmedia.moviedetail.presentation.OverviewState.Collapsed
import com.example.idnmedia.moviedetail.presentation.OverviewState.Expanded
import com.example.idnmedia.moviedetail.presentation.adapter.ReviewAdapter
import com.example.idnmedia.moviedetail.presentation.userreview.UserReviewFragment.Companion.keyMovieId
import com.example.idnmedia.utils.ImageUtils.loadImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.min

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModels()

    private var reviewAdapter: ReviewAdapter? = null

    private var overviewState: OverviewState = Collapsed

    private var player: YouTubePlayer? = null

    override fun initBinding(inflater: LayoutInflater): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater)
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    override fun FragmentMovieDetailBinding.initView() {
        reviewAdapter = ReviewAdapter()

        reviewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewAdapter
        }
        overview.addToggleClickListener()

    }

    override fun observeData() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            dispatchMovieDetail(it)
        }
        val movieId = arguments?.getLong(keyMovieId) ?: -1
        viewModel.loadMovieDetail(movieId)
    }

    private fun dispatchMovieDetail(movie: MovieDetail) {
        binding?.apply {
            loader.isVisible = false
            contentView.isVisible = true
            backdropImage.isVisible = true

            renderMovieInfo(movie)
            renderVoteInfo(movie)
            renderReviewInfo(movie.reviews)
            loadImages(movie)
            initializeYoutubePlayerIfRequired()

            reviewCta.setOnClickListener {
                findNavController().navigate(
                    gotoReviewScreen(movie.id, movie.title)
                )
            }
        }
    }

    private fun FragmentMovieDetailBinding.renderMovieInfo(movie: MovieDetail) {
        title.text = movie.title
        overview.text = movie.overview

        genre.text = concatGenre(movie.genres)
        duration.text = calculateDurationInString(movie.runtime)

        btnPlay.isVisible = !movie.trailerKey.isNullOrEmpty()
        trailerNotFoundInfo.isVisible = movie.trailerKey.isNullOrEmpty()

        btnPlay.setOnClickListener { playPlayer(movie.trailerKey) }
    }

    private fun FragmentMovieDetailBinding.renderVoteInfo(movie: MovieDetail) {
        voteRating.rating = (movie.voteAverage.toFloat() * 10) / 100 * 5
        voteRatingText.text = String.format("%.1f", movie.voteAverage)
        voteCount.text = getString(R.string.voters_count_label, movie.voteCount)
    }

    private fun FragmentMovieDetailBinding.renderReviewInfo(review: MovieReviews?) {
        reviewInfo.text =
            getString(R.string.review_info_label, review?.totalReview ?: 0)

        reviewCta.isVisible = (review?.totalReview ?: 0) > 3 == true

        review?.let {
            reviewAdapter?.submitList(it.reviews.take(min(it.reviews.size, 3)))
        }
    }

    private fun FragmentMovieDetailBinding.loadImages(movie: MovieDetail) {
        loadImage(
            context = requireContext(),
            imageView = backdropImage,
            path = movie.backdropPath,
            onSuccess = ::setupStatusBarColor
        )

        loadImage(
            context = requireContext(),
            imageView = posterImage,
            path = movie.posterPath
        )
    }

    private fun calculateDurationInString(duration: Int): String {
        if (duration < 60) return "$duration minutes"

        val hours = duration / 60
        val minute = duration % 60

        return "$hours hr $minute min"
    }

    private fun concatGenre(genres: List<Genre>): String {
        return genres.joinToString { it.name }
    }

    private fun AppCompatTextView.addToggleClickListener() {
        this.setOnClickListener {
            if (overviewState == Collapsed) {
                maxLines = Int.MAX_VALUE
                ellipsize = null
                overviewState = Expanded
            } else {
                maxLines = 3
                ellipsize = TextUtils.TruncateAt.END
                overviewState = Collapsed
            }
        }
    }

    private fun setupStatusBarColor(bitmap: Bitmap?) {
        if (bitmap == null) return

        Palette.from(bitmap).generate().apply {
            requireActivity().window.statusBarColor =
                getLightMutedColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.white
                    )
                )
        }
    }

    private fun initializeYoutubePlayerIfRequired() {
        binding?.trailerPlayer?.apply {
            lifecycle.addObserver(this)
            addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    player = youTubePlayer
                }
            })
        }

    }

    private fun playPlayer(trailerKey: String?) {
        if (trailerKey.isNullOrEmpty()) {
            return
        }

        binding?.apply {
            btnPlay.isVisible = false
            backdropImage.isVisible = false
            trailerPlayer.isVisible = true
        }
        player?.loadVideo(trailerKey, 0f)
    }

    private fun releasePlayer() {
        binding?.trailerPlayer?.release()
    }
}

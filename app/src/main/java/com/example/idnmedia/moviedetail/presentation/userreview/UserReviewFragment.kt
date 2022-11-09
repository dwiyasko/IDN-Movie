package com.example.idnmedia.moviedetail.presentation.userreview

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.idnmedia.common.BaseFragment
import com.example.idnmedia.databinding.FragmentUserReviewBinding
import com.example.idnmedia.moviedetail.presentation.adapter.PagingReviewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserReviewFragment : BaseFragment<FragmentUserReviewBinding>() {

    private val viewModel: UserReviewViewModel by viewModels()
    private var reviewAdapter: PagingReviewAdapter? = null

    override fun initBinding(inflater: LayoutInflater): FragmentUserReviewBinding {
        return FragmentUserReviewBinding.inflate(inflater)
    }

    override fun FragmentUserReviewBinding.initView() {
        reviewAdapter = PagingReviewAdapter()

        reviewList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reviewAdapter
        }

        val movieTitle = arguments?.getString(keyMovieTitle, "")
        title.text = movieTitle
    }

    override fun observeData() {
        val movieId = arguments?.getLong(keyMovieId) ?: -1
        lifecycleScope.launch {
            viewModel.loadReviews(movieId).collectLatest {
                reviewAdapter?.submitData(it)
            }
        }
    }

    override fun onPause() {
        reviewAdapter = null
        super.onPause()
    }

    companion object {
        const val keyMovieId = "key.movieId"
        const val keyMovieTitle = "key.movieTitle"
    }
}

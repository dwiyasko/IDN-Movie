package com.example.idnmedia.movielist.presentation

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.example.idnmedia.common.BaseFragment
import com.example.idnmedia.databinding.FragmentMainBinding
import com.example.idnmedia.movielist.domain.model.Movie
import com.example.idnmedia.movielist.presentation.MovieListFragmentDirections.gotoMovieDetail
import com.example.idnmedia.movielist.presentation.adapter.MovieAdapter
import com.example.idnmedia.movielist.presentation.adapter.MovieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMainBinding>() {

    private var movieAdapter: MovieAdapter? = null

    private val viewModel: MovieListViewModel by viewModels()

    override fun initBinding(inflater: LayoutInflater): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater)
    }

    override fun FragmentMainBinding.initView() {
        movieAdapter = MovieAdapter {
            findNavController().navigate(gotoMovieDetail(it.id))
        }
        movieAdapter?.setUpListener()

        movieList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieAdapter
        }
    }

    override fun observeData() {
        lifecycleScope.launch {
            viewModel.loadTopRatedMovies().collectLatest {
                movieAdapter?.submitData(it)
            }
        }
    }

    private fun PagingDataAdapter<Movie, MovieViewHolder>.setUpListener() {
        addLoadStateListener { state ->
            binding?.loader?.isVisible = itemCount == 0
            binding?.movieList?.isVisible = itemCount > 0
        }
    }
}
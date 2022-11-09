package com.example.idnmedia.movielist.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.idnmedia.databinding.ViewMovieItemBinding
import com.example.idnmedia.movielist.domain.model.Movie
import com.example.idnmedia.utils.ImageUtils.loadImage

class MovieViewHolder(
    private val binding: ViewMovieItemBinding,
    private val onClickItemListener: (Movie) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        loadImage(itemView.context, binding.posterImage, movie.posterPath)
        binding.posterImage.setOnClickListener {
            onClickItemListener(movie)
        }
    }
}

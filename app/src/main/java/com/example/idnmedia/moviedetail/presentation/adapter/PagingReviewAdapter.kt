package com.example.idnmedia.moviedetail.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.idnmedia.databinding.ViewReviewItemBinding
import com.example.idnmedia.moviedetail.domain.model.Review

class PagingReviewAdapter : PagingDataAdapter<Review, ReviewViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ViewReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }
}

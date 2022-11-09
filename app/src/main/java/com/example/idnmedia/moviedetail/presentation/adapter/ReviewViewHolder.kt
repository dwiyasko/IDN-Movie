package com.example.idnmedia.moviedetail.presentation.adapter

import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_COMPACT
import android.text.TextUtils
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idnmedia.R
import com.example.idnmedia.databinding.ViewReviewItemBinding
import com.example.idnmedia.moviedetail.domain.model.Review
import com.example.idnmedia.utils.DateUtils.toDateString
import com.example.idnmedia.utils.ImageUtils.loadCircleImage
import com.example.idnmedia.utils.ImageUtils.loadDrawableImage

class ReviewViewHolder(
    private val binding: ViewReviewItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Review) {
        binding.apply {
            loadImage(avatarImage, item.authorAva)
            authorName.text = item.authorName
            postedDate.text =
                itemView.context.getString(R.string.posted_at_label, item.createdAt.toDateString())
            ratingValue.text = item.rating.toString()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                reviewContent.text = Html.fromHtml(item.content, FROM_HTML_MODE_COMPACT)
            } else {
                reviewContent.text = Html.fromHtml(item.content)
            }

            reviewContent.addToggleClickListener()
        }
    }

    private fun loadImage(onView: ImageView, url: String?) {
        if (url == null) {
            loadDrawableImage(itemView.context, onView, R.drawable.ava_sample)
            return
        }

        loadCircleImage(itemView.context, onView, url)
    }

    private fun AppCompatTextView.addToggleClickListener() {
        this.setOnClickListener {
            if (maxLines > 3) {
                maxLines = 3
                ellipsize = TextUtils.TruncateAt.END
            } else {
                maxLines = Int.MAX_VALUE
                ellipsize = null
            }
        }
    }
}

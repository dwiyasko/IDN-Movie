package com.example.idnmedia.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.idnmedia.utils.Constant.imageUrlPrefix

object ImageUtils {

    fun loadImage(
        context: Context,
        imageView: ImageView,
        path: String,
        onSuccess: (Bitmap?) -> Unit = {}
    ) {
        Glide.with(context)
            .asBitmap()
            .load("$imageUrlPrefix$path")
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(resource)
                    return false
                }

            })
            .into(imageView)
    }

    fun loadCircleImage(
        context: Context,
        imageView: ImageView,
        path: String,
        onSuccess: (Bitmap?) -> Unit = {}
    ) {
        Glide.with(context)
            .asBitmap()
            .load("$imageUrlPrefix$path")
            .apply(RequestOptions.circleCropTransform())
            .listener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    onSuccess(resource)
                    return false
                }

            })
            .into(imageView)
    }

    fun loadDrawableImage(context: Context, imageView: ImageView, @DrawableRes drawable: Int) {
        Glide.with(context)
            .load(drawable)
            .into(imageView)
    }
}

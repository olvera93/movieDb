package com.olvera.moviedb.util

import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("android:imageUrl")
fun loadImage(imageView: ImageView, url: String?) { // Es para visualizar la imagen en el ImageView
    Glide.with(imageView.context)
        .load(Constant.IMAGE_BASE_URL + Constant.IMAGE_W500 + url)
        .into(imageView)
}
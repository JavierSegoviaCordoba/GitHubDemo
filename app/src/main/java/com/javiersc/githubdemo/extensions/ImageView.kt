package com.javiersc.githubdemo.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("url_circle")
fun ImageView.urlCircle(imageUrl: String?) =
    Glide.with(context).load(imageUrl).transition(withCrossFade()).apply(RequestOptions().circleCrop()).into(this)

@BindingAdapter("url")
fun ImageView.url(imageUrl: String?) = Glide.with(context).load(imageUrl).transition(withCrossFade()).into(this)
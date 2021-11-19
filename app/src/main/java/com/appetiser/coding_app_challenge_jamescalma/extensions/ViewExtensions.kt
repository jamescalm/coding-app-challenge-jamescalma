package com.appetiser.coding_app_challenge_jamescalma.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadItemImage(uri: String?){
    val options = RequestOptions()
        .dontAnimate()
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .dontAnimate()
        .fitCenter()
        .centerCrop()
        .into(this)
}
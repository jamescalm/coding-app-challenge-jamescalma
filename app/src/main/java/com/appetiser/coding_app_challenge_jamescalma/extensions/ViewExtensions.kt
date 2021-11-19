package com.appetiser.coding_app_challenge_jamescalma.extensions

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/** This function loads the image from glide with normal center crop image
 *  this also places the placeholder when Glide encounters an error*/
fun ImageView.loadItemImage(uri: String?, drawable: Drawable){
    Glide.with(this.context)
        .load(uri)
        .dontAnimate()
        .fitCenter()
        .centerCrop()
        .error(drawable)
        .into(this)
}

/** This function loads the image from glide with circle crop image*/
fun ImageView.loadItemImageCircleCrop(uri: String?, drawable: Drawable){
    Glide.with(this.context)
        .load(uri)
        .dontAnimate()
        .fitCenter()
        .circleCrop()
        .error(drawable)
        .into(this)
}
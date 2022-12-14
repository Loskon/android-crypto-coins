package com.loskon.cryptocoins.utils

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.loskon.cryptocoins.R

object ImageLoader {

    fun load(view: ImageView, url: String) {
        view.load(url) {
            crossfade(false)
            placeholder(R.drawable.placeholder)
            transformations(CircleCropTransformation())
        }
    }
}
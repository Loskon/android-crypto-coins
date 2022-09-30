package com.loskon.cryptocoins.utils

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

object ImageLoader {

    fun load(view: ImageView, url: String) {
        view.load(url) {
            crossfade(false)
            transformations(CircleCropTransformation())
        }
    }
}
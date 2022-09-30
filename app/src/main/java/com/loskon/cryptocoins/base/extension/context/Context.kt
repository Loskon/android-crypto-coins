package com.loskon.cryptocoins.base.extension.context

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorKtx(@ColorRes colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}
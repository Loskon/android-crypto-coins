package com.loskon.cryptocoins.base.widget

import android.content.res.ColorStateList
import android.view.View
import com.google.android.material.snackbar.Snackbar

open class BaseSnackbar {

    private var snackbar: Snackbar? = null

    fun make(view: View, message: String, length: Int = Snackbar.LENGTH_LONG): BaseSnackbar {
        snackbar = Snackbar.make(view, message, length)
        return this
    }

    fun setBackgroundTintList(color: Int) {
        snackbar?.view?.backgroundTintList = ColorStateList.valueOf(color)
    }

    fun show() = snackbar?.show()

    fun dismiss() = snackbar?.dismiss()

    inline fun create(functions: BaseSnackbar.() -> Unit): BaseSnackbar {
        this.functions()
        return this
    }
}
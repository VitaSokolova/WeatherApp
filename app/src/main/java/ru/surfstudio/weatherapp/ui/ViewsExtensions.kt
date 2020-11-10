package ru.surfstudio.weatherapp.ui

import android.graphics.drawable.Drawable
import android.widget.TextView

fun TextView.setEndCompoundDrawable(drawable: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(
        null,
        null,
        drawable,
        null
    )
}

fun TextView.setStartCompoundDrawable(drawable: Drawable) {
    setCompoundDrawablesWithIntrinsicBounds(
        drawable,
        null,
        null,
        null
    )
}
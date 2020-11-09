package ru.surfstudio.weatherapp.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val id: String,
    val name: String,
    val country: String,
    val colorHex: String
) :Parcelable
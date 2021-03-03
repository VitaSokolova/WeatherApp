package ru.surfstudio.weatherapp.ui.common

import android.content.Context

/**
 * Abstraction< which describes provider to resources
 */
interface ResourcesProvider {

    fun getString(resId: Int): String
}

/**
 * Android platform implementation of ResourcesProvider
 */
class ResourcesProviderImpl(val context: Context) : ResourcesProvider {

    override fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }
}
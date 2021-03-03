package ru.surfstudio.weatherapp.domain.converters

private const val KILOMETERS_IN_MILE = 1.609344

/**
 *  Utility class for converting measures of length
 */
object DistanceConverter {
    fun convertMilesToKilometers(miles: Double): Double {
        return miles * KILOMETERS_IN_MILE
    }
}
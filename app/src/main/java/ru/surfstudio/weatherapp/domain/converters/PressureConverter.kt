package ru.surfstudio.weatherapp.domain.converters

private const val ONE_MBAR_IN_MILLIMETERS = 0.750062

/**
 *  Утилитарный класс для конвертации мер давления
 */
object PressureConverter {
    fun convertMbarToMillimeters(value: Double): Double {
        return value * ONE_MBAR_IN_MILLIMETERS
    }
}
package ru.surfstudio.weatherapp.domain.converters

private const val ONE_MILE_PER_HOUR_IN_METERS_IN_SECOND = 0.44704

/**
 *  Утилитарный класс для конвертации мер скорости
 */
object SpeedConverter {
    fun convertMilesPerHourToMetersInSecond(speedInMiles: Double): Double {
        return speedInMiles * ONE_MILE_PER_HOUR_IN_METERS_IN_SECOND
    }
}
package ru.surfstudio.weatherapp.domain.weather

import org.threeten.bp.LocalDate

/**
 * Daily forecast
 *
 * @property date of forecast
 * @property weatherState
 * @property windDirection intercardinal and cardinal direction
 * @property windSpeed in meters per second
 * @property currentTemperature on the Celsius scale
 * @property dayTemperature on the Celsius scale
 * @property nightTemperature  on the Celsius scale
 * @property visibility maximum visible distance in kilometers
 * @property humidity in percents
 * @property airPressure in millimetres of mercury
 */
data class DailyForecast(
    val date: LocalDate,
    val weatherState: WeatherState,
    val windDirection: WindDirection,
    val windSpeed: Double,  // метры/секунду
    val currentTemperature: Double, // градусы Цельсия
    val dayTemperature: Double, // градусы Цельсия
    val nightTemperature: Double, // градусы Цельсия
    val visibility: Double,
    val humidity: Int,
    val airPressure: Double
) {

    fun getWindSpeedInPercents(): Double = windSpeed / MAX_WIND_SPEED_IN_METERS_PER_SECOND

    fun getWindVisibilityInPercents(): Double = visibility / MAX_VISIBILITY_IN_KILOMETERS

    fun getAirPressureInPercents(): Double {
        return (airPressure - MIN_AIR_PRESSURE_IN_MILLIMETERS) / (MAX_AIR_PRESSURE_IN_MILLIMETERS - MIN_AIR_PRESSURE_IN_MILLIMETERS)
    }
}
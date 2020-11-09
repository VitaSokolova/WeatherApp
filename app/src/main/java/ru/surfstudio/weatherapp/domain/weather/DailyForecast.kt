package ru.surfstudio.weatherapp.domain.weather

import org.threeten.bp.LocalDate

/**
 * Прогноз на день
 */
class DailyForecast(
    val date: LocalDate,
    val weatherState: WeatherState,
    val windDirection: WindDirection,
    val windSpeed: Double,  // метры/секунду
    val dayTemperature: Double, // градусы Цельсия
    val nightTemperature: Double, // градусы Цельсия
    val visibility: Double, // километры
    val humidity: Int, // %
    val airPressure: Double // мм. рт. ст.
) {

    fun getWindSpeedInPercents(): Double = windSpeed / MAX_WIND_SPEED_IN_METERS_PER_SECOND

    fun getWindVisibilityInPercents(): Double = visibility / MAX_VISIBILITY_IN_KILOMETERS

    fun getAirPressureInPercents(): Double {
        return (airPressure - MIN_AIR_PRESSURE_IN_MILLIMETERS) / (MAX_AIR_PRESSURE_IN_MILLIMETERS - MIN_AIR_PRESSURE_IN_MILLIMETERS)
    }
}
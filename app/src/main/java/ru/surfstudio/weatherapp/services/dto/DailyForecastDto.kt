package ru.surfstudio.weatherapp.services.dto

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.domain.weather.WindDirection
import ru.surfstudio.weatherapp.domain.converters.DistanceConverter
import ru.surfstudio.weatherapp.domain.converters.PressureConverter
import ru.surfstudio.weatherapp.domain.converters.SpeedConverter
import ru.surfstudio.weatherapp.services.mappers.WeatherStateMapper

/**
 * Model for parsing the weather forecast for the day
 */
class DailyForecastDto(
    @SerializedName("applicable_date")
    val date: String,
    @SerializedName("weather_state_abbr")
    val weatherState: String,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,  // mph
    @SerializedName("the_temp")
    val currentTemperature: Double, // on the Celsius scale
    @SerializedName("max_temp")
    val dayTemperature: Double, // on the Celsius scale
    @SerializedName("min_temp")
    val nightTemperature: Double, // on the Celsius scale
    @SerializedName("visibility")
    val visibility: Double, // miles
    @SerializedName("humidity")
    val humidity: Int, // %
    @SerializedName("air_pressure")
    val airPressure: Double // mbar
) {

    fun transform(): DailyForecast {
        return DailyForecast(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE),
            weatherState = WeatherStateMapper.map(weatherState),
            windDirection = WindDirection.getByDegrees(windDirection),
            windSpeed = SpeedConverter.convertMilesPerHourToMetersPerSecond(windSpeed),
            currentTemperature = currentTemperature,
            dayTemperature = dayTemperature,
            nightTemperature = nightTemperature,
            visibility = DistanceConverter.convertMilesToKilometers(visibility),
            humidity = humidity,
            airPressure = PressureConverter.convertMbarToMillimeters(airPressure)
        )
    }
}
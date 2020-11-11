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
 * Модель для маппинга прогноза погоды на день
 */
class DailyForecastDto(
    @SerializedName("applicable_date")
    val date: String,
    @SerializedName("weather_state_abbr")
    val weatherState: String,
    @SerializedName("wind_direction")
    val windDirection: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,  // миль / ч
    @SerializedName("the_temp")
    val currentTemperature: Double, // градусы Цельсия
    @SerializedName("max_temp")
    val dayTemperature: Double, // градусы Цельсия
    @SerializedName("min_temp")
    val nightTemperature: Double, // градусы Цельсия
    @SerializedName("visibility")
    val visibility: Double, // мили
    @SerializedName("humidity")
    val humidity: Int, // %
    @SerializedName("air_pressure")
    val airPressure: Double // мбар
) {
    fun transform(): DailyForecast {
        return DailyForecast(
            date = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE),
            weatherState = WeatherStateMapper.map(weatherState),
            windDirection = WindDirection.getByDegrees(windDirection),
            windSpeed = SpeedConverter.convertMilesPerHourToMetersInSecond(windSpeed),
            currentTemperature = currentTemperature,
            dayTemperature = dayTemperature,
            nightTemperature = nightTemperature,
            visibility = DistanceConverter.convertMilesToKilometers(visibility),
            humidity = humidity,
            airPressure = PressureConverter.convertMbarToMillimeters(airPressure)
        )
    }
}
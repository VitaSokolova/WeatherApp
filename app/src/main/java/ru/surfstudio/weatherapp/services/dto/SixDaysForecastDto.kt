package ru.surfstudio.weatherapp.services.dto

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import ru.surfstudio.weatherapp.domain.converters.DistanceConverter
import ru.surfstudio.weatherapp.domain.converters.PressureConverter
import ru.surfstudio.weatherapp.domain.converters.SpeedConverter
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.domain.weather.WindDirection
import ru.surfstudio.weatherapp.services.mappers.WeatherStateMapper


class SixDaysForecastDto(
    @SerializedName("consolidated_weather")
    val forecastList: List<DailyForecastDto>
) {
    fun transform(): List<DailyForecast> {
        return forecastList.map {
            it.transform()
        }
    }
}

//"consolidated_weather": [
//{
//    "id": 6697591128457216,
//    "weather_state_name": "Showers",
//    "weather_state_abbr": "s",
//    "wind_direction_compass": "S",
//    "created": "2020-11-11T15:20:03.074472Z",
//    "applicable_date": "2020-11-11",
//    "min_temp": 9.5,
//    "max_temp": 13.6,
//    "the_temp": 13.465,
//    "wind_speed": 6.676180154622339,
//    "wind_direction": 172.52415427742324,
//    "air_pressure": 1017.5,
//    "humidity": 77,
//    "visibility": 8.971667959118747,
//    "predictability": 73
//}
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
package ru.surfstudio.weatherapp.ui

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import org.threeten.bp.DayOfWeek
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.weather.WeatherState
import ru.surfstudio.weatherapp.domain.weather.WindDirection

fun DayOfWeek.getName(context: Context): String {
    return context.resources.getString(
        when (this) {
            DayOfWeek.MONDAY -> R.string.monday
            DayOfWeek.TUESDAY -> R.string.tuesday
            DayOfWeek.WEDNESDAY -> R.string.wednesday
            DayOfWeek.THURSDAY -> R.string.thursday
            DayOfWeek.FRIDAY -> R.string.friday
            DayOfWeek.SATURDAY -> R.string.sunday
            DayOfWeek.SUNDAY -> R.string.saturday
        }
    )
}

fun WeatherState.getWeatherDescription(context: Context): String {
    return context.resources.getString(
        when (this) {
            WeatherState.CLEAR -> R.string.clear_weather_state
            WeatherState.LIGHT_CLOUDS -> R.string.light_clouds_weather_state
            WeatherState.HEAVY_CLOUDS -> R.string.heavy_clouds_weather_state
            WeatherState.RAIN -> R.string.rain_weather_state
            WeatherState.SNOW -> R.string.snow_weather_state
            WeatherState.THUNDER -> R.string.thunder_weather_state
        }
    )
}

fun WeatherState.getWeatherIcon(context: Context): Drawable {
    return ContextCompat.getDrawable(
        context,
        when (this) {
            WeatherState.CLEAR -> R.drawable.ic_sun
            WeatherState.LIGHT_CLOUDS -> R.drawable.ic_sun_and_cloud
            WeatherState.HEAVY_CLOUDS -> R.drawable.ic_clouds
            WeatherState.RAIN -> R.drawable.ic_rain
            WeatherState.SNOW -> R.drawable.ic_snow
            WeatherState.THUNDER -> R.drawable.ic_rain
        }
    ) ?: error("Error occurred while getting drawable weather icon")
}

fun WeatherState.getWeatherBackground(context: Context): Drawable? {
    return ContextCompat.getDrawable(
        context,
        when (this) {
            WeatherState.CLEAR -> R.drawable.sun
            WeatherState.LIGHT_CLOUDS -> R.drawable.light_clouds
            WeatherState.HEAVY_CLOUDS -> R.drawable.heavy_clouds
            WeatherState.RAIN -> R.drawable.rain
            WeatherState.SNOW -> R.drawable.snow
            WeatherState.THUNDER -> R.drawable.thunder
        }
    )
}

fun WeatherState.getWeatherStatusBarColor(context: Context): Int {
    return ContextCompat.getColor(
        context,
        when (this) {
            WeatherState.CLEAR -> R.color.sunny
            WeatherState.LIGHT_CLOUDS -> R.color.light_clouds
            WeatherState.HEAVY_CLOUDS -> R.color.heavy_clouds
            WeatherState.RAIN -> R.color.rain
            WeatherState.SNOW -> R.color.snow
            WeatherState.THUNDER -> R.color.thunder
        }
    )
}

fun WindDirection.getAbbreviation(context: Context): String {
    return context.resources.getString(
        when (this) {
            WindDirection.NORTH -> R.string.wind_abbreviation_north
            WindDirection.NORTHEAST -> R.string.wind_abbreviation_northeast
            WindDirection.EAST -> R.string.wind_abbreviation_east
            WindDirection.SOUTHEAST -> R.string.wind_abbreviation_southeast
            WindDirection.SOUTH -> R.string.wind_abbreviation_south
            WindDirection.SOUTHWEST -> R.string.wind_abbreviation_southwest
            WindDirection.WEST -> R.string.wind_abbreviation_west
            WindDirection.NORTHWEST -> R.string.wind_abbreviation_northwest
        }
    )
}
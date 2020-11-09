package ru.surfstudio.weatherapp.services.dto

import com.google.gson.annotations.SerializedName
import ru.surfstudio.weatherapp.domain.weather.DailyForecast

class SixDaysForecastDto(
    @SerializedName("consolidated_weather")
    val list: List<DailyForecastDto>
) {
    fun transform(): List<DailyForecast> {
        return list.map { it.transform() }
    }
}
package ru.surfstudio.weatherapp.ui.city.model

import org.threeten.bp.LocalDate
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.ui.common.LoadStatus

class WeatherInCityUi(
    val forecasts: List<DailyForecast>,
    val loadStatus: LoadStatus
) {

    fun getTodayForecast(): DailyForecast? {
        return forecasts.first { it.date.isEqual(LocalDate.now()) }
    }

    fun getNextFiveDaysForecasts(): List<DailyForecast> {
        return forecasts.filter { it.date.isAfter(LocalDate.now()) }
    }
}
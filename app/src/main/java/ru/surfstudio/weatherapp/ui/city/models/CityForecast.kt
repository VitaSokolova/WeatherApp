package ru.surfstudio.weatherapp.ui.city.models

import org.threeten.bp.LocalDate
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.ui.common.LoadStatus
import ru.surfstudio.weatherapp.ui.common.LoadableData

data class CityForecast(
    val forecast: List<DailyForecast>? = null,
    override val loadStatus: LoadStatus
) : LoadableData {

    fun getTodayForecast(): DailyForecast? {
        return forecast?.find { it.date == LocalDate.now() }
    }

    fun getOtherDaysForecast(): List<DailyForecast> {
        return forecast?.filter { it.date != LocalDate.now() } ?: emptyList()
    }
}
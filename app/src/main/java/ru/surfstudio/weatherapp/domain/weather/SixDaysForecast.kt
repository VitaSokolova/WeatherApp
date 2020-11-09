package ru.surfstudio.weatherapp.domain.weather

import org.threeten.bp.LocalDate
import ru.surfstudio.weatherapp.domain.common.LoadableData
import ru.surfstudio.weatherapp.domain.common.Normal

class SixDaysForecast(
    val loadableData: LoadableData<List<DailyForecast>>
) {

    fun getTodayForecast(): DailyForecast? {
        return if (loadableData is Normal) {
            loadableData.data.find { it.date == LocalDate.now() }
        } else {
            null
        }
    }

    fun getOtherDaysForecast(): List<DailyForecast> {
        return if (loadableData is Normal) {
            loadableData.data.filter { it.date != LocalDate.now() }
        } else {
            emptyList()
        }
    }
}
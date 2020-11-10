package ru.surfstudio.weatherapp.services

import io.reactivex.Single
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.services.common.RetrofitHolder.retrofit

/**
 * Репозиторий для загрузки прогноза погоды из сети
 */
class ForecastRepository {

    private val service: ForecastService by lazy {
        retrofit.create(ForecastService::class.java)
    }

    fun getSixDaysForecast(cityId: String): Single<List<DailyForecast>> {
        return service.getFiveDaysForecast(cityId).map { it.transform() }
    }
}
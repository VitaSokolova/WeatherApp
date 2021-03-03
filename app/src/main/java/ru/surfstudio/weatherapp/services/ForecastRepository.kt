package ru.surfstudio.weatherapp.services

import io.reactivex.Single
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.services.common.RetrofitHolder.retrofit

/**
 * Repository for downloading weather forecasts from the web
 */
class ForecastRepository {

    private val service: ForecastService by lazy {
        retrofit.create(ForecastService::class.java)
    }

    fun getSixDaysForecast(cityId: String): Single<List<DailyForecast>> {
        return service.getFiveDaysForecast(cityId).map { it.transform() }
    }
}
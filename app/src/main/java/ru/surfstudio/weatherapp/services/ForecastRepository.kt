package ru.surfstudio.weatherapp.services

import io.reactivex.Single
import ru.surfstudio.weatherapp.domain.weather.DailyForecast

class ForecastRepository {

    val service: ForecastService by lazy {
        RetrofitHolder.retrofit.create(ForecastService::class.java)
    }

    fun getSixDaysForecast(cityId: String): Single<List<DailyForecast>> {
        return service.getSixDaysForecast(cityId).map {
            it.transform()
        }
    }
}
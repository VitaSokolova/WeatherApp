package ru.surfstudio.weatherapp.services

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.surfstudio.weatherapp.services.dto.SixDaysForecastDto

interface ForecastService {

    @GET(SIX_DAYS_FORECAST)
    fun getSixDaysForecast(@Path("cityId") cityId: String): Single<SixDaysForecastDto>
}
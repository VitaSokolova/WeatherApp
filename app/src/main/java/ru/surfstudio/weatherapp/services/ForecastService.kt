package ru.surfstudio.weatherapp.services

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.surfstudio.weatherapp.services.common.FORECAST_URL
import ru.surfstudio.weatherapp.services.dto.DailyForecastDto
import ru.surfstudio.weatherapp.services.dto.SixDaysForecastDto

public interface ForecastService {
    @GET(FORECAST_URL)
    fun getFiveDaysForecast(@Path("cityId") cityId: String?): Single<SixDaysForecastDto>
}
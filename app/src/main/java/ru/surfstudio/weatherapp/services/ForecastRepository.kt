package ru.surfstudio.weatherapp.services

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.surfstudio.weatherapp.domain.common.Error
import ru.surfstudio.weatherapp.domain.common.Loading
import ru.surfstudio.weatherapp.domain.common.Normal
import ru.surfstudio.weatherapp.domain.weather.SixDaysForecast
import ru.surfstudio.weatherapp.services.common.RetrofitCreator.retrofit
import ru.surfstudio.weatherapp.services.dto.SixDaysForecastDto

class ForecastRepository {

    private val service: ForecastService by lazy {
        retrofit.create(ForecastService::class.java)
    }

    fun getFiveDaysForecast(cityId: String): MutableLiveData<SixDaysForecast> {
        val result = MutableLiveData(SixDaysForecast(loadableData = Loading()))
        service.getFiveDaysForecast(cityId).enqueue(
            object : Callback<SixDaysForecastDto> {
                override fun onResponse(
                    call: Call<SixDaysForecastDto>,
                    response: Response<SixDaysForecastDto>
                ) {
                    val forecast = response.body()?.transform()
                    result.value = SixDaysForecast(forecast?.let { Normal(it) } ?: Error())
                }

                override fun onFailure(call: Call<SixDaysForecastDto>, t: Throwable) {
                    result.value = SixDaysForecast(Error(t))
                    Log.e("NETWORK ERROR", t.message + t.stackTraceToString())
                }

            }
        )
        return result
    }
}
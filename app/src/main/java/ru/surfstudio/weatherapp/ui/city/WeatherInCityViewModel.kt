package ru.surfstudio.weatherapp.ui.city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.services.ForecastRepository
import ru.surfstudio.weatherapp.ui.city.models.CityForecast
import ru.surfstudio.weatherapp.ui.common.LoadStatus

/**
 * ViewModel я экрана погоды в городе [WeatherInCityActivity]
 */
class WeatherInCityViewModel(
    private val forecastRepository: ForecastRepository,
    cityFromRoute: City
) : ViewModel() {

    val city: LiveData<City> = MutableLiveData(cityFromRoute)

    val forecast: LiveData<CityForecast>
        get() = _forecast
    private val _forecast = MutableLiveData<CityForecast>()

    private val disposables = CompositeDisposable()

    init {
        loadForecast(cityFromRoute.id)
    }

    private fun loadForecast(cityId: String) {
        disposables.add(
            forecastRepository.getSixDaysForecast(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _forecast.setValue(CityForecast(loadStatus = LoadStatus.LOADING)) }
                .subscribe(
                    { forecast ->
                        _forecast.setValue(CityForecast(forecast, LoadStatus.NORMAL))
                    }
                ) { throwable ->
                    Log.e("NETWORK ERROR", throwable.message + throwable.stackTraceToString())
                    _forecast.setValue(CityForecast(loadStatus = LoadStatus.ERROR))
                }
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}
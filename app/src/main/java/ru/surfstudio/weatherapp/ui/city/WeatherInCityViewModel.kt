package ru.surfstudio.weatherapp.ui.city

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.domain.weather.WeatherState
import ru.surfstudio.weatherapp.domain.weather.WindDirection
import ru.surfstudio.weatherapp.services.ForecastRepository
import ru.surfstudio.weatherapp.ui.city.model.WeatherInCityUi
import ru.surfstudio.weatherapp.ui.common.LoadStatus

class WeatherInCityViewModel(city: City, val repository: ForecastRepository) : ViewModel() {

    val cityLiveData: LiveData<City> = MutableLiveData<City>(city)

    val forecastLiveData: LiveData<WeatherInCityUi>
        get() = _forecastLiveData
    private val _forecastLiveData: MutableLiveData<WeatherInCityUi> = MutableLiveData()

    private val disposables = CompositeDisposable()

    init {
        Log.v("WEATHER_APP", "ViewModel created")
        loadSixDaysForecast(city.id)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    private fun loadSixDaysForecast(cityId: String) {
        disposables.add(
            repository.getSixDaysForecast(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _forecastLiveData.value = WeatherInCityUi(emptyList(), LoadStatus.LOADING)
                }
                .subscribe(
                    { forecastList ->
                        _forecastLiveData.value = WeatherInCityUi(forecastList, LoadStatus.NORMAL)
                    },
                    { error ->
                        _forecastLiveData.value = WeatherInCityUi(emptyList(), LoadStatus.ERROR)
                        Log.e("WEATHER_APP", error.message + error.stackTrace)
                    }
                )
        )
    }
}
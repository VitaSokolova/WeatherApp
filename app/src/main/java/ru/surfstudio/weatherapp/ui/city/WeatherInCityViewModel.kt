package ru.surfstudio.weatherapp.ui.city

import androidx.lifecycle.*
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.domain.weather.SixDaysForecast
import ru.surfstudio.weatherapp.services.ForecastRepository

class WeatherInCityViewModel(
    private val forecastRepository: ForecastRepository,
    private val cityFromRoute: City
) : ViewModel() {

    val city: MutableLiveData<City> = MutableLiveData(cityFromRoute)
    val forecast: LiveData<SixDaysForecast> = Transformations.switchMap(
        city
    ) { city -> forecastRepository.getFiveDaysForecast(city.id) }

}
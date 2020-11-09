package ru.surfstudio.weatherapp.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.surfstudio.weatherapp.domain.City
import ru.surfstudio.weatherapp.services.ForecastRepository

class WeatherInCityViewModelFactory(val city: City) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeatherInCityViewModel(ForecastRepository(), city) as T
    }
}
package ru.surfstudio.weatherapp.ui.city

import android.content.Context
import android.content.Intent
import ru.surfstudio.weatherapp.domain.City

private const val EXTRA_CITY = "EXTRA_CITY"

class WeatherInCityRoute(val city: City) {

    constructor(intent: Intent) : this(
        intent.getParcelableExtra<City>(EXTRA_CITY) ?: error(" invalid input data")
    )

    fun getIntent(context: Context): Intent {
        return Intent(context, WeatherInCityActivity::class.java).apply {
            putExtra(EXTRA_CITY, city)
        }
    }
}
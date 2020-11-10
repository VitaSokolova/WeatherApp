package ru.surfstudio.weatherapp.ui.city

import android.content.Context
import android.content.Intent
import ru.surfstudio.weatherapp.domain.City

private const val CITY_ID_EXTRA = "CITY_ID_EXTRA"

/**
 * Навигация на экран с погодой в городе [WeatherInCityActivity]
 */
class WeatherInCityRoute(val city: City) {

    fun getIntent(context: Context): Intent {
        return Intent(context, WeatherInCityActivity::class.java).apply {
            putExtra(CITY_ID_EXTRA, city)
        }
    }

    companion object {
        fun fromIntent(intent: Intent): WeatherInCityRoute {
            return WeatherInCityRoute(
                intent.getParcelableExtra(CITY_ID_EXTRA)
                    ?: error("Incorrect CITY_ID_EXTRA extra in WeatherInCityRoute")
            )
        }
    }
}
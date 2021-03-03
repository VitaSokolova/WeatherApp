package ru.surfstudio.weatherapp.domain.weather

/**
 *  Weather states, supported in app
 *
 *  Each state has unique design
 */
enum class WeatherState {
    CLEAR,
    LIGHT_CLOUDS,
    HEAVY_CLOUDS,
    RAIN,
    SNOW,
    THUNDER
}
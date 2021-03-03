package ru.surfstudio.weatherapp.services.mappers

import ru.surfstudio.weatherapp.domain.weather.WeatherState

object WeatherStateMapper {

    /**
     * Translate server constants into a domain model
     */
    fun map(value:String): WeatherState {
        return when (value) {
            "c" -> WeatherState.CLEAR
            "lc" -> WeatherState.LIGHT_CLOUDS
            "hc" -> WeatherState.HEAVY_CLOUDS
            "t" -> WeatherState.THUNDER
            in arrayOf("hr", "lr", "s") -> WeatherState.RAIN
            in arrayOf("sn", "sl", "h") -> WeatherState.SNOW
            else -> error("Unknown weather type")
        }
    }
}
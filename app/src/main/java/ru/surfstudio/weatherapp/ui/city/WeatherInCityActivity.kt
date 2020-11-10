package ru.surfstudio.weatherapp.ui.city

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.threeten.bp.LocalDate
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.ui.*
import ru.surfstudio.weatherapp.ui.city.customviews.DayOfWeekForecastView
import ru.surfstudio.weatherapp.ui.city.customviews.WeatherParamView
import ru.surfstudio.weatherapp.ui.city.models.CityForecast
import ru.surfstudio.weatherapp.ui.common.LoadStatus
import java.text.DecimalFormat
import kotlin.math.roundToInt

class WeatherInCityActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var rootContainer: ViewGroup
    private lateinit var temperatureTv: TextView
    private lateinit var descriptionTv: TextView
    private lateinit var minMaxTv: TextView

    private lateinit var humidityTv: WeatherParamView
    private lateinit var airPressureTv: WeatherParamView
    private lateinit var windTv: WeatherParamView
    private lateinit var visibilityTv: WeatherParamView

    private lateinit var weatherParamsViews: List<WeatherParamView>

    private lateinit var day1ForecastView: DayOfWeekForecastView
    private lateinit var day2ForecastView: DayOfWeekForecastView
    private lateinit var day3ForecastView: DayOfWeekForecastView
    private lateinit var day4ForecastView: DayOfWeekForecastView
    private lateinit var day5ForecastView: DayOfWeekForecastView

    private lateinit var forecastsViews: List<DayOfWeekForecastView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_in_city)

        findViews()
        initListeners()
    }

    private fun findViews() {
        toolbar = findViewById(R.id.toolbar)
        rootContainer = findViewById(R.id.weather_in_city_root_container)
        temperatureTv = findViewById(R.id.weather_in_city_temperature_tv)
        descriptionTv = findViewById(R.id.weather_in_city_description_tv)
        minMaxTv = findViewById(R.id.weather_in_city_min_max_tv)

        humidityTv = findViewById(R.id.weather_in_city_humidity_param)
        airPressureTv = findViewById(R.id.weather_in_city_air_pressure_param)
        windTv = findViewById(R.id.weather_in_city_wind_param)
        visibilityTv = findViewById(R.id.weather_in_city_visibility_param)

        weatherParamsViews = listOf(humidityTv, airPressureTv, windTv, visibilityTv)

        day1ForecastView = findViewById(R.id.weather_in_city_forecast_1)
        day2ForecastView = findViewById(R.id.weather_in_city_forecast_2)
        day3ForecastView = findViewById(R.id.weather_in_city_forecast_3)
        day4ForecastView = findViewById(R.id.weather_in_city_forecast_4)
        day5ForecastView = findViewById(R.id.weather_in_city_forecast_5)

        forecastsViews = listOf(
            day1ForecastView,
            day2ForecastView,
            day3ForecastView,
            day4ForecastView,
            day5ForecastView
        )
    }

    private fun initListeners() {
        toolbar.setNavigationOnClickListener { finish() }
    }
}
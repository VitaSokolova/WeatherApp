package ru.surfstudio.weatherapp.ui.city

import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import ru.surfstudio.weatherapp.R
import ru.surfstudio.weatherapp.domain.weather.DailyForecast
import ru.surfstudio.weatherapp.ui.*
import ru.surfstudio.weatherapp.ui.city.customviews.DayOfWeekForecastView
import ru.surfstudio.weatherapp.ui.city.customviews.WeatherParamView
import ru.surfstudio.weatherapp.ui.city.models.CityForecast
import ru.surfstudio.weatherapp.ui.common.data.LoadStatus
import java.text.DecimalFormat

/**
 * Screen with detailed forecast in the city
 */
class WeatherInCityActivity : AppCompatActivity() {

    private lateinit var viewModel: WeatherInCityViewModel

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

    // It is better to use RecyclerView in such case, but it is out of your agenda today
    private lateinit var day1ForecastView: DayOfWeekForecastView
    private lateinit var day2ForecastView: DayOfWeekForecastView
    private lateinit var day3ForecastView: DayOfWeekForecastView
    private lateinit var day4ForecastView: DayOfWeekForecastView
    private lateinit var day5ForecastView: DayOfWeekForecastView

    private lateinit var forecastsViews: List<DayOfWeekForecastView>

    private val decimalFormat = DecimalFormat().apply { maximumFractionDigits = 2 }
    private val intFormat = DecimalFormat().apply { maximumFractionDigits = 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_in_city)

        val route = WeatherInCityRoute(intent)
        viewModel = ViewModelProvider(this, WeatherInCityViewModelFactory(route.city)).get(
            WeatherInCityViewModel::class.java
        )

        findViews()
        initListeners()
        observeViewModel()
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

    private fun observeViewModel() {
        with(viewModel) {
            city.observe(
                this@WeatherInCityActivity,
                Observer { city ->
                    toolbar.title = city.name
                }
            )

            forecast.observe(
                this@WeatherInCityActivity,
                Observer { forecast ->
                    when (forecast.loadStatus) {
                        LoadStatus.LOADING -> renderLoading()
                        LoadStatus.NORMAL -> renderData(forecast)
                        LoadStatus.ERROR -> renderError()
                    }
                }
            )
        }
    }

    private fun renderLoading() {
        descriptionTv.text = resources.getString(R.string.loading_text)

        weatherParamsViews.forEach { it.renderEmptyState() }

        val today = LocalDate.now()
        forecastsViews.forEachIndexed { i, view ->
            val day = today.plusDays(i + 1L).dayOfWeek
            val dayName = day.getDisplayName(TextStyle.FULL, getCurrentLocale())
            view.renderEmptyState(dayName)
        }
    }

    private fun renderData(forecast: CityForecast) {
        forecast.getTodayForecast()?.let {
            renderTodayWeather(it)
        }
        renderOtherDaysForecasts(forecast)
    }

    private fun renderTodayWeather(it: DailyForecast) {
        rootContainer.background = it.weatherState.getWeatherBackground(this)

        temperatureTv.text = resources.getString(
            R.string.degree_format,
            it.currentTemperature
        )

        descriptionTv.text = it.weatherState.getWeatherDescription(this)
        descriptionTv.setStartCompoundDrawable(it.weatherState.getWeatherIcon(this))

        minMaxTv.text = resources.getString(
            R.string.min_max_text_format,
            it.dayTemperature,
            it.nightTemperature
        )

        humidityTv.renderParams(
            "${it.humidity}%",
            it.humidity / 100.0
        )
        airPressureTv.renderParams(
            decimalFormat.format(it.airPressure),
            it.getAirPressureInPercents()
        )
        windTv.renderParams(
            "${it.windDirection.getAbbreviation(this)}, ${intFormat.format(it.windSpeed)}",
            it.getWindSpeedInPercents()
        )
        visibilityTv.renderParams(
            intFormat.format(it.visibility),
            it.getWindVisibilityInPercents()
        )
    }

    private fun renderOtherDaysForecasts(forecast: CityForecast) {
        forecast.getOtherDaysForecast().forEachIndexed { index, dailyForecast ->
            forecastsViews[index].renderParams(
                dailyForecast.date.dayOfWeek.getDisplayName(TextStyle.FULL, getCurrentLocale()),
                dailyForecast.weatherState.getWeatherIcon(this),
                dailyForecast.dayTemperature,
                dailyForecast.nightTemperature
            )
        }
    }

    private fun renderError() {
        descriptionTv.text = resources.getString(R.string.error_text)
    }
}
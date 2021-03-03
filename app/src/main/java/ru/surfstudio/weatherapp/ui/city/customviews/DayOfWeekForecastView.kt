package ru.surfstudio.weatherapp.ui.city.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import ru.surfstudio.weatherapp.R

/**
 * Custom View for displaying one day forecasr in list
 */
class DayOfWeekForecastView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val dayOfWeekTv: TextView
    private val iconIv: ImageView
    private val dayTemperatureTv: TextView
    private val nightTemperatureTv: TextView

    init {
        inflate(context, R.layout.day_of_week_forecast_view, this)
        orientation = HORIZONTAL

        dayOfWeekTv = findViewById(R.id.day_of_week_forecast_title_tv)
        iconIv = findViewById(R.id.day_of_week_forecast_iv)
        dayTemperatureTv = findViewById(R.id.day_of_week_day_temperature_tv)
        nightTemperatureTv = findViewById(R.id.day_of_week_night_temperature_tv)
    }

    fun renderParams(
        dayOnWeekName: String,
        icon: Drawable,
        dayTemperature: Double,
        nightTemperature: Double
    ) {
        iconIv.isInvisible = false

        dayOfWeekTv.text = dayOnWeekName
        iconIv.setImageDrawable(icon)
        dayTemperatureTv.text = resources.getString(R.string.degree_format, dayTemperature)
        nightTemperatureTv.text = resources.getString(R.string.degree_format, nightTemperature)
    }

    fun renderEmptyState(dayOnWeekName: String) {
        dayOfWeekTv.text = dayOnWeekName
        dayTemperatureTv.text = "–"
        nightTemperatureTv.text = "–"
        iconIv.isInvisible = true
    }
}
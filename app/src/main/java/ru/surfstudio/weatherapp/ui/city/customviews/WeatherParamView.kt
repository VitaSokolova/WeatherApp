package ru.surfstudio.weatherapp.ui.city.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.withStyledAttributes
import ru.surfstudio.weatherapp.R

/**
 * Custom View для отображения параметра погоды
 */
class WeatherParamView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val hintTv: TextView
    private val valueTv: TextView
    private val progressBar: ProgressBar

    init {
        inflate(context, R.layout.weather_param_view, this)

        hintTv = findViewById(R.id.weather_param_hint_tv)
        valueTv = findViewById(R.id.weather_param_value_tv)
        progressBar = findViewById(R.id.weather_param_pb)

        context.withStyledAttributes(attrs, R.styleable.WeatherParamView) {
            hintTv.text = getString(R.styleable.WeatherParamView_hintText)
        }
    }

    fun renderParams(value: String, progress: Double) {
        progressBar.progress = progress
        valueTv.text = value
    }

    fun renderEmptyState() {
        progressBar.progress = 0.5
        valueTv.text = "–"
    }
}
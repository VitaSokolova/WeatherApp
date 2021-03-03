package ru.surfstudio.weatherapp.ui.city.customviews

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ru.surfstudio.weatherapp.R

/**
 * Custom view with vertical progress bar
 *
 * @property progress indicates the percent of progress bar filling (in range  0..100)
 */
class ProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progressBkgColor: Int = ContextCompat.getColor(context, R.color.white_40)
    private var progressColor: Int = ContextCompat.getColor(context, R.color.white)
    private var cornerRadius: Float =
        resources.getDimensionPixelSize(R.dimen.progress_bar_corner_radius).toFloat()

    private var paint = Paint().apply { Paint.Style.FILL_AND_STROKE }

    var progress: Double = .0
        set(value) {
            field = value.coerceIn(.0, 100.0)
            invalidate()
        }

    init {
        context.withStyledAttributes(attrs, R.styleable.ProgressBar) {
            progressBkgColor = getColor(
                R.styleable.ProgressBar_backgroundColor,
                ContextCompat.getColor(context, R.color.white_40)
            )
            progressColor = getColor(
                R.styleable.ProgressBar_progressColor,
                ContextCompat.getColor(context, R.color.white)
            )
            cornerRadius = getDimensionPixelSize(
                R.styleable.ProgressBar_cornerRadius,
                resources.getDimensionPixelSize(R.dimen.progress_bar_corner_radius)
            ).toFloat()
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawBackground(canvas)
        drawProgress(canvas)
    }

    /**
     * Draw a blank progress bar on the background
     */
    private fun drawBackground(canvas: Canvas) {
        paint.color = progressBkgColor
        canvas.drawRoundRect(
            RectF(0f, 0f, width.toFloat(), height.toFloat()),
            cornerRadius,
            cornerRadius,
            paint
        )
    }

    /**
     * Draw progress rectangle
     */
    private fun drawProgress(canvas: Canvas) {
        paint.color = progressColor

        val progressEndY = height.toFloat() - (progress * height).toFloat()

        canvas.drawRoundRect(
            RectF(0f, progressEndY, width.toFloat(), height.toFloat()),
            cornerRadius,
            cornerRadius,
            paint
        )
    }
}
package ru.surfstudio.weatherapp.ui

import android.content.Context
import org.threeten.bp.DayOfWeek
import ru.surfstudio.weatherapp.R

fun DayOfWeek.getName(context: Context): String {
    return context.resources.getString(
        when (this) {
            DayOfWeek.MONDAY -> R.string.monday
            DayOfWeek.TUESDAY -> R.string.tuesday
            DayOfWeek.WEDNESDAY -> R.string.wednesday
            DayOfWeek.THURSDAY -> R.string.thursday
            DayOfWeek.FRIDAY -> R.string.friday
            DayOfWeek.SATURDAY -> R.string.sunday
            DayOfWeek.SUNDAY -> R.string.saturday
        }
    )
}
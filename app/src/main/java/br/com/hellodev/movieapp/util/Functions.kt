package br.com.hellodev.movieapp.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun formatCommentDate(date: String?): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val providedDate = date?.let { dateFormat.parse(it) }
    val currentDate = Date()

    val calendarProvided = Calendar.getInstance()
    val calendarCurrent = Calendar.getInstance()
    providedDate?.let { calendarProvided.time = it }
    calendarCurrent.time = currentDate

    val yearDifference = calendarCurrent.get(Calendar.YEAR) - calendarProvided.get(Calendar.YEAR)
    val monthDifference = calendarCurrent.get(Calendar.MONTH) - calendarProvided.get(Calendar.MONTH)
    val dayDifference =
        calendarCurrent.get(Calendar.DAY_OF_MONTH) - calendarProvided.get(Calendar.DAY_OF_MONTH)

    val totalDaysDifference = yearDifference * 365 + monthDifference * 30 + dayDifference

    return when {
        totalDaysDifference == 0 -> "Hoje"
        totalDaysDifference == 1 -> "Ontem"
        totalDaysDifference < 31 -> "$totalDaysDifference dias atrás"
        else -> {
            val monthsDifference = totalDaysDifference / 30
            if (monthsDifference == 1) {
                "1 mês atrás"
            } else {
                "$monthsDifference meses atrás"
            }
        }
    }
}

fun applyScreenWindowInsets(
    view: View,
    applyTop: Boolean = true,
    applyBottom: Boolean = true,
) {
    // Obter as margens iniciais definidas no XML
    val initialLayoutParams = (view.layoutParams as? ViewGroup.MarginLayoutParams)?.let {
        it.topMargin to it.bottomMargin
    } ?: (0 to 0)

    ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

        val topMargin = if (applyTop) insets.top + initialLayoutParams.first else initialLayoutParams.first
        val bottomMargin = if (applyBottom) insets.bottom + initialLayoutParams.second else initialLayoutParams.second

        v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            this.topMargin = topMargin
            this.bottomMargin = bottomMargin
        }

        WindowInsetsCompat.CONSUMED
    }
}

fun applyComponentWindowInsets(
    view: View,
    applyTop: Boolean = true,
    applyBottom: Boolean = true
) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
        val bars = insets.getInsets(
            WindowInsetsCompat.Type.systemBars()
                    or WindowInsetsCompat.Type.displayCutout()
        )
        v.updatePadding(
            top = if (applyTop) bars.top else 0,
            bottom = if (applyBottom) bars.bottom else 0
        )
        WindowInsetsCompat.CONSUMED
    }
}
package br.com.hellodev.movieapp.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import br.com.hellodev.movieapp.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Fragment.initToolbar(
    toolbar: Toolbar,
    showIconNavigation: Boolean = true,
    lightIcon: Boolean = false
) {
    val iconBack = if(lightIcon) R.drawable.ic_back_white else R.drawable.ic_back

    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title = ""

    if (showIconNavigation) {
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(iconBack)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }
}

fun Fragment.showSnackBar(
    message: Int,
    duration: Int = Snackbar.LENGTH_SHORT
) {
    view?.let { Snackbar.make(it, message, duration).show() }
}

fun String.isEmailValid(): Boolean {
    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    return emailPattern.matches(this)
}

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
    val dayDifference = calendarCurrent.get(Calendar.DAY_OF_MONTH) - calendarProvided.get(Calendar.DAY_OF_MONTH)

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

fun Double.calculateFileSize(): String {
    val value = this * 10.0

    return if (value >= 1000) {
        String.format("%.2f GB", value / 1000)
    } else {
        String.format("%.1f MB", value)
    }
}

fun Int.calculateMovieTime(): String {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}m"
}
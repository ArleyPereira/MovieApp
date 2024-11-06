package br.com.hellodev.movieapp.util

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.hellodev.movieapp.R
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable

fun Fragment.initToolbar(
    toolbar: Toolbar,
    showIconNavigation: Boolean = true,
    lightIcon: Boolean = false
) {
    val iconBack = if (lightIcon) R.drawable.ic_back_white else R.drawable.ic_back

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

fun Context.circularProgressDrawable(): Drawable {
    return CircularProgressDrawable(this).apply {
        strokeWidth = 12f
        centerRadius = 60f
        setColorSchemeColors(
            ContextCompat.getColor(
                this@circularProgressDrawable,
                R.color.color_default
            )
        )
        start()
    }
}

fun NavController.onNavigate(action: Int) {
    this.navigate(
        action,
        null,
        NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
            .build()
    )
}

fun NavController.onNavigate(nav: NavDirections) {
    this.navigate(
        nav.actionId,
        nav.arguments,
        NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
            .build()
    )
}

inline fun <reified T : Serializable> Intent.getSerializableCompat(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(
        key,
        T::class.java
    )

    else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}





















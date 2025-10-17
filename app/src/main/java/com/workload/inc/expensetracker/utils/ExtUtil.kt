package com.workload.inc.expensetracker.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
fun Activity.setFullScreen() {
    when {
        // Android 15 (API 35) and above
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM -> {
            // New way: use WindowInsetsController
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        // Android 11 to 14
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }

        // Below Android 11 (legacy)
        else -> {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun View.setSafeOnClickListener(
    interval: Long = 2000,
    listener: (View) -> Unit
) {
    this.setOnClickListener {
        this.isEnabled = false
        listener(it)
        this.postDelayed({ this.isEnabled = true }, interval)
    }
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.goneView() {
    this.visibility = View.GONE
}

fun String.withCurrency(currency: String): String {
    return "$this $currency"
}
package com.workload.inc.expensetracker.utils

import android.annotation.SuppressLint
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtils {

    @SuppressLint("SimpleDateFormat")
    fun formatDateFromMillis(
        millis: Long = System.currentTimeMillis(),
        format: String = "yyyy-MM-dd"
    ): String {
        val sdf = SimpleDateFormat(format)
        return sdf.format(Date(millis))
    }

    @SuppressLint("SimpleDateFormat")
    fun formatTime12HourFromMillis(
        millis: Long = System.currentTimeMillis()
    ): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date(millis))
    }

}
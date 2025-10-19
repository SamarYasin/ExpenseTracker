package com.workload.inc.expensetracker.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntryModel
import com.workload.inc.expensetracker.receiver.MidnightAlarmReceiver
import java.util.Calendar

object AlarmScheduler {
    @SuppressLint("ScheduleExactAlarm")
    fun scheduleDailyAlarm(context: Context, expenses: DailyExpenseEntryModel? = null) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MidnightAlarmReceiver::class.java)

        expenses?.let {
            intent.putExtra("residenceExpense", it.residenceExpense)
            intent.putExtra("foodExpense", it.foodExpense)
            intent.putExtra("entertainmentExpense", it.entertainmentExpense)
            intent.putExtra("insuranceExpense", it.insuranceExpense)
            intent.putExtra("utilitiesExpense", it.utilitiesExpense)
            intent.putExtra("personalCareExpense", it.personalCareExpense)
            intent.putExtra("educationExpense", it.educationExpense)
            intent.putExtra("healthExpense", it.healthExpense)
            intent.putExtra("othersExpense", it.othersExpense)
            intent.putExtra("fuelExpense", it.fuelExpense)
            intent.putExtra("clothingExpense", it.clothingExpense)
            intent.putExtra("shoppingExpense", it.shoppingExpense)
            intent.putExtra("giftsDonationsExpense", it.giftsDonationsExpense)
            intent.putExtra("travelExpense", it.travelExpense)
            intent.putExtra("taxesExpense", it.taxesExpense)
            intent.putExtra("date", it.date)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Calculate trigger time (same as before)
        val now = Calendar.getInstance()
        val triggerTime = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            if (before(now)) {
                add(Calendar.DATE, 1)
            }
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime.timeInMillis,
            pendingIntent
        )
    }
}
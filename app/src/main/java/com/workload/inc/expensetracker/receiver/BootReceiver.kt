package com.workload.inc.expensetracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.workload.inc.expensetracker.utils.AlarmScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            AlarmScheduler.scheduleDailyAlarm(context)
        }
    }
}
package com.workload.inc.expensetracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntryModel
import com.workload.inc.expensetracker.localDb.room.ExpenseTrackerDataBase
import com.workload.inc.expensetracker.utils.AlarmScheduler
import com.workload.inc.expensetracker.utils.DateUtils.formatDateFromMillis
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MidnightAlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        CoroutineScope(Dispatchers.IO).launch {
            val db = ExpenseTrackerDataBase.getInstance(context)

            val entry = DailyExpenseEntryModel(
                residenceExpense = intent?.getStringExtra("residenceExpense") ?: "0",
                foodExpense = intent?.getStringExtra("foodExpense") ?: "0",
                entertainmentExpense = intent?.getStringExtra("entertainmentExpense") ?: "0",
                insuranceExpense = intent?.getStringExtra("insuranceExpense") ?: "0",
                utilitiesExpense = intent?.getStringExtra("utilitiesExpense") ?: "0",
                personalCareExpense = intent?.getStringExtra("personalCareExpense") ?: "0",
                educationExpense = intent?.getStringExtra("educationExpense") ?: "0",
                healthExpense = intent?.getStringExtra("healthExpense") ?: "0",
                othersExpense = intent?.getStringExtra("othersExpense") ?: "0",
                fuelExpense = intent?.getStringExtra("fuelExpense") ?: "0",
                clothingExpense = intent?.getStringExtra("clothingExpense") ?: "0",
                shoppingExpense = intent?.getStringExtra("shoppingExpense") ?: "0",
                giftsDonationsExpense = intent?.getStringExtra("giftsDonationsExpense") ?: "0",
                travelExpense = intent?.getStringExtra("travelExpense") ?: "0",
                taxesExpense = intent?.getStringExtra("taxesExpense") ?: "0",
                date = intent?.getStringExtra("date") ?: formatDateFromMillis(System.currentTimeMillis())
            )

            db.dailyExpenseEntryDao().insert(entry)

        }

        AlarmScheduler.scheduleDailyAlarm(context)
    }

}
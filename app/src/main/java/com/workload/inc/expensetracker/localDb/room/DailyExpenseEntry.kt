package com.workload.inc.expensetracker.localDb.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("daily_expense_table")
data class DailyExpenseEntry(
    var residenceExpense: String = "",
    var foodExpense: String = "",
    var entertainmentExpense: String = "",
    var insuranceExpense: String = "",
    var utilitiesExpense: String = "",
    var personalCareExpense: String = "",
    var educationExpense: String = "",
    var healthExpense: String = "",
    var othersExpense: String = "",
    var fuelExpense: String = "",
    var clothingExpense: String = "",
    var shoppingExpense: String = "",
    var giftsDonationsExpense: String = "",
    var travelExpense: String = "",
    var taxesExpense: String = "",
    var date: String = "",

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
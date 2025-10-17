package com.workload.inc.expensetracker.localDb.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_finance_table")
data class UserFinanceModel(
    @PrimaryKey val id: Int = 1,
    val totalIncome: Int = 0,
    val totalExpense: Int = 0,
    val balance: Int = 0,
    val budget: Int = 0
)
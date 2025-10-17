package com.workload.inc.expensetracker.localDb.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("expense_entry_table")
data class ExpenseEntry(
    var expenseType: String = "",
    var expenseDetail: String = "",
    var expenseAmount: String = "",
    var date: String = "",
    var time: String = "",

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
)
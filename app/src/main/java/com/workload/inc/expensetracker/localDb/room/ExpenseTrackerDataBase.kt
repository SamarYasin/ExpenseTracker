package com.workload.inc.expensetracker.localDb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DailyExpenseEntry::class, ExpenseEntry::class], version = 1, exportSchema = true)
abstract class ExpenseTrackerDataBase : RoomDatabase() {

    abstract fun dailyExpenseDao(): DailyExpenseDao
    abstract fun expenseEntryDao(): ExpenseEntryDao

    companion object {
        @Volatile private var INSTANCE: ExpenseTrackerDataBase? = null

        fun getInstance(context: Context): ExpenseTrackerDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseTrackerDataBase::class.java,
                    "expense_tracker_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
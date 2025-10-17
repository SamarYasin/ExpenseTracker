package com.workload.inc.expensetracker.localDb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DailyExpenseEntryModel::class, ExpenseEntryModel::class, UserFinanceModel::class],
    version = 1,
    exportSchema = true
)
abstract class ExpenseTrackerDataBase : RoomDatabase() {

    abstract fun dailyExpenseEntryDao(): DailyExpenseEntryDao
    abstract fun expenseEntryDao(): ExpenseEntryDao
    abstract fun userFinanceDao(): UserFinanceDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseTrackerDataBase? = null

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
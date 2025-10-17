package com.workload.inc.expensetracker.localDb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DailyExpenseDao {

    @Query("SELECT * FROM daily_expense_table")
    suspend fun getAll(): List<DailyExpenseEntry>

    @Insert
    suspend fun insert(vararg dailyExpenseEntry: DailyExpenseEntry)

    @Update
    suspend fun update(dailyExpenseEntry: DailyExpenseEntry)

    @Delete
    suspend fun delete(dailyExpenseEntry: DailyExpenseEntry)

    @Query("DELETE FROM daily_expense_table")
    suspend fun deleteAll()

}
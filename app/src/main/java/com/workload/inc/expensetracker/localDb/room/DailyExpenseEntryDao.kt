package com.workload.inc.expensetracker.localDb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DailyExpenseEntryDao {

    @Query("SELECT * FROM daily_expense_entry_table")
    suspend fun getAll(): List<DailyExpenseEntryModel>

    @Insert
    suspend fun insert(vararg dailyExpenseEntryModel: DailyExpenseEntryModel)

    @Update
    suspend fun update(dailyExpenseEntryModel: DailyExpenseEntryModel)

    @Delete
    suspend fun delete(dailyExpenseEntryModel: DailyExpenseEntryModel)

    @Query("DELETE FROM daily_expense_entry_table")
    suspend fun deleteAll()

}
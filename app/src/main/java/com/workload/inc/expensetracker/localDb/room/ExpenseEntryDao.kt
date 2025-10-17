package com.workload.inc.expensetracker.localDb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseEntryDao {

    @Query("SELECT * FROM expense_entry_table")
    suspend fun getAll(): List<ExpenseEntryModel>

    @Query("SELECT * FROM expense_entry_table WHERE date = :date")
    suspend fun getAllByDate(date: String): List<ExpenseEntryModel>

    @Insert
    suspend fun insert(vararg expenseEntryModel: ExpenseEntryModel)

    @Update
    suspend fun update(expenseEntryModel: ExpenseEntryModel)

    @Delete
    suspend fun delete(expenseEntryModel: ExpenseEntryModel)

    @Query("DELETE FROM expense_entry_table")
    suspend fun deleteAll()

}
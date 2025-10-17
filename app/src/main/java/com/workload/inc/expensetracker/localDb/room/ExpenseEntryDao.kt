package com.workload.inc.expensetracker.localDb.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseEntryDao {

    @Query("SELECT * FROM expense_entry_table")
    suspend fun getAll(): List<ExpenseEntry>

    @Query("SELECT * FROM expense_entry_table WHERE date = :date")
    suspend fun getAllByDate(date: String): List<ExpenseEntry>

    @Insert
    suspend fun insert(vararg expenseEntry: ExpenseEntry)

    @Update
    suspend fun update(expenseEntry: ExpenseEntry)

    @Delete
    suspend fun delete(expenseEntry: ExpenseEntry)

    @Query("DELETE FROM expense_entry_table")
    suspend fun deleteAll()

}
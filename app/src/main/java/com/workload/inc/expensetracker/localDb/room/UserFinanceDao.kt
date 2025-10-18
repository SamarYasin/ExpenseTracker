package com.workload.inc.expensetracker.localDb.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserFinanceDao {

    @Query("SELECT * FROM user_finance_table WHERE id = 1 LIMIT 1")
    suspend fun getUserFinance(): UserFinanceModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserFinance(userFinanceModel: UserFinanceModel)

}
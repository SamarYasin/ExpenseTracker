package com.workload.inc.expensetracker.di

import android.content.Context
import androidx.room.Room
import com.workload.inc.expensetracker.localDb.room.DailyExpenseDao
import com.workload.inc.expensetracker.localDb.room.ExpenseEntryDao
import com.workload.inc.expensetracker.localDb.room.ExpenseTrackerDataBase
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ExpenseTrackerDataBase {
        return Room.databaseBuilder(
            context,
            ExpenseTrackerDataBase::class.java,
            "expense_tracker_database"
        ).build()
    }

    @Provides
    fun provideDailyExpenseDao(db: ExpenseTrackerDataBase): DailyExpenseDao {
        return db.dailyExpenseDao()
    }

    @Provides
    fun provideExpenseEntryDao(db: ExpenseTrackerDataBase): ExpenseEntryDao {
        return db.expenseEntryDao()
    }

    @Provides
    @Singleton
    fun provideAppSharedPref(@ApplicationContext context: Context): AppSharedPref {
        return AppSharedPref(context)
    }

}
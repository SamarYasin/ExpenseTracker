package com.workload.inc.expensetracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workload.inc.expensetracker.localDb.room.DailyExpenseDao
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntry
import com.workload.inc.expensetracker.localDb.room.ExpenseEntry
import com.workload.inc.expensetracker.localDb.room.ExpenseEntryDao
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPref
import com.workload.inc.expensetracker.validator.UserInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSharedPref: AppSharedPref,
    private val userInputValidator: UserInputValidator,
    private val dailyExpenseDao: DailyExpenseDao,
    private val expenseEntryDao: ExpenseEntryDao,
) : ViewModel() {

    private val TAG = "MainViewModel"
    private var _expense: MutableLiveData<List<DailyExpenseEntry>> = MutableLiveData()
    val expense: LiveData<List<DailyExpenseEntry>> get() = _expense
    private var _todayExpense: MutableLiveData<List<ExpenseEntry>> = MutableLiveData()
    val todayExpense: LiveData<List<ExpenseEntry>> get() = _todayExpense

    fun setValue(key: String, value: String) {
        appSharedPref.putString(key, value)
    }

    fun getValue(key: String): String? {
        return appSharedPref.getString(key, "")
    }

    fun setBoolean(key: String, value: Boolean) {
        appSharedPref.putBoolean(key, value)
    }

    fun getBoolean(key: String): Boolean {
        return appSharedPref.getBoolean(key, false)
    }

    fun getExpenseForToday(date: String) {
        viewModelScope.launch {
            val expense = expenseEntryDao.getAllByDate(date)
            _todayExpense.value = expense
        }
    }

    fun addExpense(expenseEntry: ExpenseEntry, formattedDate : String) {
        viewModelScope.launch {
            expenseEntryDao.insert(expenseEntry)
            getExpenseForToday(formattedDate)
        }
    }

    fun addDailyExpenseEntry(dailyExpenseEntry: DailyExpenseEntry) {
        viewModelScope.launch {
            dailyExpenseDao.insert(dailyExpenseEntry)
        }
    }

    fun getAllDailyExpenseEntries() {
        viewModelScope.launch {
            val expenseList = dailyExpenseDao.getAll()
            _expense.value = expenseList
        }
    }

}
package com.workload.inc.expensetracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntryDao
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntryModel
import com.workload.inc.expensetracker.localDb.room.ExpenseEntryModel
import com.workload.inc.expensetracker.localDb.room.ExpenseEntryDao
import com.workload.inc.expensetracker.localDb.room.UserFinanceDao
import com.workload.inc.expensetracker.localDb.room.UserFinanceModel
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPref
import com.workload.inc.expensetracker.validator.UserInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSharedPref: AppSharedPref,
    private val userInputValidator: UserInputValidator,
    private val dailyExpenseDao: DailyExpenseEntryDao,
    private val expenseEntryDao: ExpenseEntryDao,
    private val userFinanceDao: UserFinanceDao,
) : ViewModel() {

    private val TAG = "MainViewModel"

    // Each Day's Total Expense Entry
    private var _dailyExpenseEntries: MutableLiveData<List<DailyExpenseEntryModel>> = MutableLiveData()
    val dailyExpenseEntries: LiveData<List<DailyExpenseEntryModel>> get() = _dailyExpenseEntries

    // Individual Expense Entries for Today
    private var _individualExpenseEntries: MutableLiveData<List<ExpenseEntryModel>> = MutableLiveData()
    val individualExpenseEntries: LiveData<List<ExpenseEntryModel>> get() = _individualExpenseEntries

    // Overall User Financial Situation, Balance, Income, Expenses, Budget
    private var _userFinancialSituation: MutableLiveData<UserFinanceModel?> = MutableLiveData()
    val userFinancialSituation: LiveData<UserFinanceModel?> get() = _userFinancialSituation

    /**
     * Best to set and get simple key-value pairs in Shared Preferences
     */
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


    /**
     * For updating user financial situation like balance, income, expenses, budget
     */

    fun getUserFinancialSituation() {
        viewModelScope.launch {
            val userFinance = userFinanceDao.getUserFinance()
            _userFinancialSituation.value = userFinance
        }
    }

    fun insertOrUpdateUserFinance(userFinanceModel: UserFinanceModel) {
        viewModelScope.launch {
            userFinanceDao.insertOrUpdateUserFinance(userFinanceModel)
            getUserFinancialSituation()
        }
    }

    /**
     * For managing individual expense entries
     */

    fun addExpense(expenseEntry: ExpenseEntryModel, formattedDate: String) {
        viewModelScope.launch {
            expenseEntryDao.insert(expenseEntry)
            getExpenseForToday(formattedDate)
        }
    }


    fun getExpenseForToday(date: String) {
        viewModelScope.launch {
            val expense = expenseEntryDao.getAllByDate(date)
            _individualExpenseEntries.value = expense
        }
    }

    /**
     * For managing daily expense entries
     */

    fun addDailyExpenseEntry(dailyExpenseEntry: DailyExpenseEntryModel) {
        viewModelScope.launch {
            dailyExpenseDao.insert(dailyExpenseEntry)
        }
    }

    fun getAllDailyExpenseEntries() {
        viewModelScope.launch {
            val expenseList = dailyExpenseDao.getAll()
            _dailyExpenseEntries.value = expenseList
        }
    }

}
package com.workload.inc.expensetracker.localDb.sharedPref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class AppSharedPref @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val appSharedPref: SharedPreferences

    init {
        appSharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        appSharedPref.edit { putString(key, value) }
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return appSharedPref.getString(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        appSharedPref.edit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return appSharedPref.getBoolean(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        appSharedPref.edit { putInt(key, value) }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return appSharedPref.getInt(key, defaultValue)
    }

    fun clear() {
        appSharedPref.edit { clear() }
    }

}
package com.workload.inc.expensetracker.utils

object CurrencyUtil {

    private var selectedCurrency: String = "USD"

    fun setCurrencyType(currency: String) {
        selectedCurrency = currency
    }

    // Extension function to use with String amount
    fun String.withCurrency(): String {
        return "$this $selectedCurrency"
    }

}
package com.workload.inc.expensetracker.data

import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.model.ExpenseModel
import com.workload.inc.expensetracker.model.LanguageModel

var languageList: List<LanguageModel> = listOf(
    LanguageModel("English", "en", false),
    LanguageModel("Español", "es", false),
    LanguageModel("বাংলা", "bn", false),
    LanguageModel("हिन्दी", "hi", false),
    LanguageModel("العربية", "ar", false),
    LanguageModel("Français", "fr", false),
    LanguageModel("中文", "zh", false),
    LanguageModel("日本語", "ja", false),
    LanguageModel("Русский", "ru", false),
    LanguageModel("اردو", "ur", false)
)

val expenseList: List<ExpenseModel> = listOf(
    ExpenseModel("Residence", R.drawable.ic_rent),
    ExpenseModel("Food", R.drawable.ic_food),
    ExpenseModel("Utilities", R.drawable.ic_utilities),
    ExpenseModel("Fuel", R.drawable.ic_fuel),
    ExpenseModel("Clothing", R.drawable.ic_clothes),
    ExpenseModel("Shopping", R.drawable.ic_shopping),
    ExpenseModel("Gifts/Donations", R.drawable.ic_gift),
    ExpenseModel("Travel", R.drawable.ic_transportation),
    ExpenseModel("Taxes", R.drawable.ic_tax),
    ExpenseModel("Insurance", R.drawable.ic_insurance),
    ExpenseModel("Entertainment", R.drawable.ic_entertainment),
    ExpenseModel("Health", R.drawable.ic_health),
    ExpenseModel("Personal Care", R.drawable.ic_persoanl_care),
    ExpenseModel("Education", R.drawable.ic_education),
    ExpenseModel("Others", R.drawable.ic_other)
)

val expenseNameList: List<String> = listOf(
    "Residence",
    "Food",
    "Utilities",
    "Fuel",
    "Clothing",
    "Shopping",
    "Gifts/Donations",
    "Travel",
    "Taxes",
    "Insurance",
    "Entertainment",
    "Health",
    "Personal Care",
    "Education",
    "Others"
)

val dateTimeFormats = listOf(
    "dd-MM-yyyy",
    "MM-dd-yyyy",
    "yyyy-MM-dd"
)

val currencyList = listOf(
    "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
    "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
    "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY",
    "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD",
    "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "FOK", "GBP", "GEL", "GGP",
    "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG",
    "HUF", "IDR", "ILS", "IMP", "INR", "IQD", "IRR", "ISK", "JMD", "JOD",
    "JPY", "KES", "KGS", "KHR", "KID", "KMF", "KRW", "KWD", "KYD", "KZT",
    "LAK", "LBP", "LKR", "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD",
    "MMK", "MNT", "MOP", "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN",
    "NAD", "NGN", "NIO", "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK",
    "PHP", "PKR", "PLN", "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR",
    "SBD", "SCR", "SDG", "SEK", "SGD", "SHP", "SLE", "SOS", "SRD", "SSP",
    "STN", "SYP", "SZL", "THB", "TJS", "TMT", "TND", "TOP", "TRY", "TTD",
    "TVD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS", "VES", "VND",
    "VUV", "WST", "XAF", "XCD", "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"
)
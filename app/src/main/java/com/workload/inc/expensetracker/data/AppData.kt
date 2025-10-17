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
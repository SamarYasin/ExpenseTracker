package com.workload.inc.expensetracker.dialog

data class WarningModel(
    var dialogMessage : String = "",
    var dialogWarningErrorIconId : Int = 0,
    var positiveButtonText : String = "",
    var negativeButtonText : String = "",
)
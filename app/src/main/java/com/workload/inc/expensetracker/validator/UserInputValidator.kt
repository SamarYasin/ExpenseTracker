package com.workload.inc.expensetracker.validator

import com.workload.inc.expensetracker.model.FieldType
import javax.inject.Inject

class UserInputValidator @Inject constructor() {

    fun checkInputField(fieldType: FieldType, value: String): String {

        return when (fieldType) {
            FieldType.NAME -> {
                validateName(value)
            }

            FieldType.EMAIL -> {
                validateEmail(value)
            }

            FieldType.PASSWORD -> {
                validatePassword(value)
            }

            FieldType.MOBILE_NUMBER -> {
                validateMobileNumber(value)
            }

            FieldType.AMOUNT -> {
                validateAmount(value)
            }
        }

    }

    private fun validateName(name: String): String {

        return if (name.isNullOrBlank()) {
            "Name cannot be empty"
        } else {
            if (name.length <= 3) {
                "Name must be at least 4 characters long"
            } else {
                ""
            }
        }

    }

    private fun validateEmail(email: String): String {
        return if (email.isNullOrBlank()) {
            "Email cannot be empty"
        } else {
            if (email.length <= 5 || !email.contains("@")) {
                "Email must be valid"
            } else {
                ""
            }
        }
    }

    private fun validatePassword(password: String): String {
        return if (password.isNullOrBlank()) {
            "Password cannot be empty"
        } else {
            if (password.length <= 5) {
                "Password must be at least 6 characters long"
            } else {
                ""
            }
        }
    }

    private fun validateMobileNumber(number: String): String {
        return if (number.isNullOrBlank()) {
            "Phone Number cannot be empty"
        } else {
            if (number.length <= 7) {
                "Phone Number must be at least 8 characters long"
            } else {
                ""
            }
        }
    }

    private fun validateAmount(amount: String): String {
        if (amount.isBlank()) {
            return "Amount cannot be empty"
        }
        val value = amount.toDoubleOrNull()
        return when {
            value == null -> "Amount must be a valid number"
            value <= 0 -> "Amount must be greater than zero"
            else -> ""
        }
    }

}
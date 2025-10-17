package com.workload.inc.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPref
import com.workload.inc.expensetracker.model.FieldType
import com.workload.inc.expensetracker.validator.UserInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appSharedPref: AppSharedPref,
    private val userInputValidator: UserInputValidator
) : ViewModel() {

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

    fun validateRegistrationInput(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        phoneNumber: String
    ): String {
        val nameError = userInputValidator.checkInputField(FieldType.NAME, name)
        if (nameError.isNotEmpty()) return nameError

        val emailError = userInputValidator.checkInputField(FieldType.EMAIL, email)
        if (emailError.isNotEmpty()) return emailError

        val passwordError = userInputValidator.checkInputField(FieldType.PASSWORD, password)
        if (passwordError.isNotEmpty()) return passwordError

        val passwordConfirmationError = userInputValidator.checkInputField(FieldType.PASSWORD, passwordConfirmation)
        if (passwordConfirmationError.isNotEmpty()) return passwordConfirmationError

        val phoneNumberError = userInputValidator.checkInputField(FieldType.MOBILE_NUMBER, phoneNumber)
        if (phoneNumberError.isNotEmpty()) return phoneNumberError

        return ""
    }

    fun validateLogInInput(
        email: String,
        password: String,
    ): String {
        val emailError = userInputValidator.checkInputField(FieldType.EMAIL, email)
        if (emailError.isNotEmpty()) return emailError

        val passwordError = userInputValidator.checkInputField(FieldType.PASSWORD, password)
        if (passwordError.isNotEmpty()) return passwordError

        return ""
    }

    fun validateForgetPasswordInput(
        name: String,
        email: String,
        phoneNumber: String
    ): String {
        val nameError = userInputValidator.checkInputField(FieldType.NAME, name)
        if (nameError.isNotEmpty()) return nameError

        val emailError = userInputValidator.checkInputField(FieldType.EMAIL, email)
        if (emailError.isNotEmpty()) return emailError

        val phoneNumberError = userInputValidator.checkInputField(FieldType.MOBILE_NUMBER, phoneNumber)
        if (phoneNumberError.isNotEmpty()) return phoneNumberError

        return ""
    }

}
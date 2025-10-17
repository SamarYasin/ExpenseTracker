package com.workload.inc.expensetracker.ui.signUp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.data.dateTimeFormats
import com.workload.inc.expensetracker.databinding.FragmentSignUpBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.CurrencyUtil
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val TAG = "SignUpFragment"
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_sign_up
    }

    override fun inflateViewBinding(): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.registerButton.setSafeOnClickListener {
            Log.d(TAG, "Register button clicked")

            val registrationError = onBoardingViewModel.validateRegistrationInput(
                name = viewBinding.nameET.text.toString(),
                email = viewBinding.emailET.text.toString(),
                password = viewBinding.passwordET.text.toString(),
                passwordConfirmation = viewBinding.passwordConfirmationET.text.toString(),
                phoneNumber = viewBinding.phoneET.text.toString()
            )

            if (registrationError.isEmpty()) {
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)

                onBoardingViewModel.setValue(AppSharedPrefKeys.USER_NAME, viewBinding.nameET.text.toString())
                onBoardingViewModel.setValue(AppSharedPrefKeys.USER_EMAIL, viewBinding.emailET.text.toString())
                onBoardingViewModel.setValue(AppSharedPrefKeys.USER_PASSWORD, viewBinding.passwordET.text.toString())
                onBoardingViewModel.setValue(AppSharedPrefKeys.PHONE_NUMBER, viewBinding.phoneET.text.toString())
                onBoardingViewModel.setValue(AppSharedPrefKeys.DATE_FORMAT, viewBinding.selectedDateFormatTV.text.toString())

            } else {
                showToast(registrationError)
            }
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            dateTimeFormats
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.dateFormatSpinner.adapter = adapter

        viewBinding.dateFormatSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedFormat = dateTimeFormats[position]
                Log.d(TAG, "Selected date format: $selectedFormat")
                viewBinding.selectedDateFormatTV.text = selectedFormat
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d(TAG, "No date format selected")
                showToast("Please select a date format")
            }
        }

    }

}
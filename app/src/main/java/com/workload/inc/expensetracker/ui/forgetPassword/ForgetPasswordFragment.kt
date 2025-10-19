package com.workload.inc.expensetracker.ui.forgetPassword

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.bottomSheet.PasswordResetBottomSheet
import com.workload.inc.expensetracker.databinding.FragmentForgetPasswordBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel

class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {

    private val TAG = "ForgetPasswordFragment"
    private var savedName: String? = null
    private var savedEmail: String? = null
    private var savedNumber: String? = null
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_forget_password
    }

    override fun inflateViewBinding(): FragmentForgetPasswordBinding {
        return FragmentForgetPasswordBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedName = onBoardingViewModel.getValue(AppSharedPrefKeys.USER_NAME)
        savedEmail = onBoardingViewModel.getValue(AppSharedPrefKeys.USER_EMAIL)
        savedNumber = onBoardingViewModel.getValue(AppSharedPrefKeys.PHONE_NUMBER)

        viewBinding.forgetPasswordButton.setSafeOnClickListener {
            Log.d(TAG, "Forget Password Button Clicked")

            if (savedName.isNullOrEmpty() or savedEmail.isNullOrEmpty() or savedNumber.isNullOrEmpty()) {
                showToast("No account found. Please sign up first.")
                return@setSafeOnClickListener
            }

            if (viewBinding.nameET.text.toString() != savedName ||
                viewBinding.emailET.text.toString() != savedEmail ||
                viewBinding.phoneET.text.toString() != savedNumber
            ) {
                showToast("Provided details do not match our records.")
                return@setSafeOnClickListener
            }

            val forgetPasswordError = onBoardingViewModel.validateForgetPasswordInput(
                name = viewBinding.nameET.text.toString(),
                email = viewBinding.emailET.text.toString(),
                phoneNumber = viewBinding.phoneET.text.toString()
            )

            if (forgetPasswordError.isEmpty()) {
                PasswordResetBottomSheet(
                    onSetPassword = { password ->
                        Log.d(TAG, "Password Reset Button Clicked")
                        onBoardingViewModel.setValue(AppSharedPrefKeys.USER_PASSWORD, password)
                        showToast("Password reset successfully.")
                        findNavController().popBackStack()
                    }
                ).show(parentFragmentManager, "PasswordResetBottomSheet")

            } else {
                showToast(forgetPasswordError)
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: Back press disabled on this screen")
            findNavController().popBackStack()
        }

    }

}
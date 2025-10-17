package com.workload.inc.expensetracker.ui.signIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentSignInBinding
import com.workload.inc.expensetracker.ui.MainActivity
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel
import kotlin.getValue

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val TAG = "SignInFragment"
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_sign_in
    }

    override fun inflateViewBinding(): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.loginButton.setSafeOnClickListener {
            Log.d(TAG, "registerButton clicked")
            val loginError = onBoardingViewModel.validateLogInInput(
                email = viewBinding.emailET.text.toString(),
                password = viewBinding.passwordET.text.toString(),
            )
            if (loginError.isEmpty()) {
                val mainIntent = Intent(activity, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(mainIntent)
            } else {
                showToast(loginError)
                //TODO: Remove below lines after testing
                val mainIntent = Intent(activity, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(mainIntent)
            }

        }

        viewBinding.signUpTVBtn.setSafeOnClickListener {
            Log.d(TAG, "signInTVBtn clicked")
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        viewBinding.forgotPasswordTVBtn.setSafeOnClickListener {
            Log.d(TAG, "forgotPasswordTVBtn clicked")
            findNavController().navigate(R.id.action_signInFragment_to_forgetPasswordFragment)
        }

    }

}
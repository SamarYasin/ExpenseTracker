package com.workload.inc.expensetracker.ui.forgetPassword

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentForgetPasswordBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel
import kotlin.getValue

class ForgetPasswordFragment : BaseFragment<FragmentForgetPasswordBinding>() {

    private val TAG = "ForgetPasswordFragment"
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_forget_password
    }

    override fun inflateViewBinding(): FragmentForgetPasswordBinding {
        return FragmentForgetPasswordBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.forgetPasswordButton.setSafeOnClickListener {
            Log.d(TAG, "Forget Password Button Clicked")

            val forgetPasswordError = onBoardingViewModel.validateForgetPasswordInput(
                name = viewBinding.nameET.text.toString(),
                email = viewBinding.emailET.text.toString(),
                phoneNumber = viewBinding.phoneET.text.toString()
            )

            if (forgetPasswordError.isEmpty()) {
                findNavController().popBackStack()
            } else {
                showToast(forgetPasswordError)
            }

        }

    }

}
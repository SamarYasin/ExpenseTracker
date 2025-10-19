package com.workload.inc.expensetracker.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.workload.inc.expensetracker.databinding.PasswordResetBottomSheetBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast

class PasswordResetBottomSheet(
    private val onSetPassword: (String) -> Unit
) : BottomSheetDialogFragment() {

    private val TAG = "PasswordResetBottomSheet"
    private var _binding: PasswordResetBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PasswordResetBottomSheetBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.crossIVBtn.setSafeOnClickListener {
            Log.d(TAG, "crossIVBtn clicked")
            dismiss()
        }

        binding.doneBtn.setSafeOnClickListener {

            if (binding.passwordET.text.isNullOrEmpty()) {
                showToast("Please enter a valid password")
                return@setSafeOnClickListener
            }

            if (binding.passwordConfirmationET.text.isNullOrEmpty()) {
                showToast("Please enter a valid password")
                return@setSafeOnClickListener
            }

            if (binding.passwordET.text.toString() != binding.passwordConfirmationET.text.toString()) {
                showToast("Passwords do not match")
                return@setSafeOnClickListener
            }

            Log.d(TAG, "Password: ${binding.passwordET.text}")

            onSetPassword.invoke(binding.passwordET.text.toString())
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
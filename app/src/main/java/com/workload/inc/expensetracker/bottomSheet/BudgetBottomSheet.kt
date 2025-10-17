package com.workload.inc.expensetracker.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.workload.inc.expensetracker.databinding.BudgetBottomSheetBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener

class BudgetBottomSheet(
    private val onAllowedBudget: ((String) -> Unit)? = null
) : BottomSheetDialogFragment() {

    private val TAG = "BudgetBottomSheet"
    private var _binding: BudgetBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BudgetBottomSheetBinding.inflate(
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
            val allowedBudget = binding.budgetET.text.toString().trim()
            Log.d(TAG, "doneBtn clicked: allowedBudget = $allowedBudget")
            onAllowedBudget?.invoke(allowedBudget)
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
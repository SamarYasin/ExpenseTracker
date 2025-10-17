package com.workload.inc.expensetracker.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.workload.inc.expensetracker.databinding.IncomeBottomSheetBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener

class IncomeBottomSheet(
    private val onSetIncome: ((String) -> Unit)? = null
) : BottomSheetDialogFragment() {

    private val TAG = "IncomeBottomSheet"
    private var _binding: IncomeBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = IncomeBottomSheetBinding.inflate(
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
            val income = binding.incomeET.text.toString()
            Log.d(TAG, "doneBtn clicked with income: $income")
            onSetIncome?.invoke(income)
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.workload.inc.expensetracker.bottomSheet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.workload.inc.expensetracker.data.currencyList
import com.workload.inc.expensetracker.databinding.InitialSettingBottomSheetBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast

class InitialSettingBottomSheet(
    private val onDoneIncome: (income: String, budget: String, currency: String) -> Unit
) : BottomSheetDialogFragment() {

    private val TAG = "InitialSettingBottomSheet"
    private var isSpinnerInitialized = false
    private var selectedCurrency: String? = null
    private var _binding: InitialSettingBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = InitialSettingBottomSheetBinding.inflate(
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
            val allowedBudget = binding.budgetET.text.toString().trim()
            if (income.isEmpty() || allowedBudget.isEmpty()) {
                Log.d(TAG, "Income or Budget is empty")
                showToast("Please enter income, budget and select currency")
                return@setSafeOnClickListener
            }
            onDoneIncome?.invoke(income, allowedBudget, selectedCurrency ?: currencyList[0])
            Log.d(
                TAG,
                "doneBtn clicked with income: $income, budget: $allowedBudget, currency: $selectedCurrency"
            )
            dismiss()
        }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.currencySpinner.adapter = adapter

        binding.currencySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (!isSpinnerInitialized) {
                        isSpinnerInitialized = true
                        return // Ignore initial call
                    }
                    selectedCurrency = currencyList[position]
                    Log.d(TAG, "Selected currency: $selectedCurrency")
                    binding.selectedDateFormatTV.text = selectedCurrency
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Optionally handle nothing selected
                }
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
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
import com.workload.inc.expensetracker.databinding.CurrencyBottomSheetBinding
import com.workload.inc.expensetracker.utils.setSafeOnClickListener

class CurrencyBottomSheet(
    private val onCurrencySelected: ((String) -> Unit)? = null
) : BottomSheetDialogFragment() {

    private val TAG = "CurrencyBottomSheet"

    private var _binding: CurrencyBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var isSpinnerInitialized = false
    private var selectedCurrency: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrencyBottomSheetBinding.inflate(
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
            Log.d(TAG, "doneBtn clicked with selected currency: $selectedCurrency")
            selectedCurrency?.let {
                onCurrencySelected?.invoke(it)
            }
            dismiss()
        }

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.currencySpinner.adapter = adapter

        binding.currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
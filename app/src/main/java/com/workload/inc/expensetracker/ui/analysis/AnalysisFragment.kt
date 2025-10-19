package com.workload.inc.expensetracker.ui.analysis

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentAnalysisBinding
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import kotlin.getValue

class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>() {

    private val TAG = "AnalysisFragment"
    private var residenceExpense: Int = 0
    private var foodExpense: Int = 0
    private var entertainmentExpense: Int = 0
    private var insuranceExpense: Int = 0
    private var utilitiesExpense: Int = 0
    private var personalCareExpense: Int = 0
    private var educationExpense: Int = 0
    private var healthExpense: Int = 0
    private var othersExpense: Int = 0
    private var fuelExpense: Int = 0
    private var clothingExpense: Int = 0
    private var shoppingExpense: Int = 0
    private var giftsDonationsExpense: Int = 0
    private var travelExpense: Int = 0
    private var taxesExpense: Int = 0
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_analysis
    }

    override fun inflateViewBinding(): FragmentAnalysisBinding {
        return FragmentAnalysisBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: Back press disabled on this screen")
            findNavController().popBackStack()
        }

    }

}
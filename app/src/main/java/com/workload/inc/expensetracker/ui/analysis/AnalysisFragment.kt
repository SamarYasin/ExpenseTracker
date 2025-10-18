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
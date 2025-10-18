package com.workload.inc.expensetracker.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentSettingBinding
import com.workload.inc.expensetracker.dialog.WarningModel
import com.workload.inc.expensetracker.dialog.view.GenericDialog
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import org.eazegraph.lib.BuildConfig
import kotlin.getValue

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private val TAG = "SettingFragment"
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_setting
    }

    override fun inflateViewBinding(): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.profileLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: profileLayout")
            findNavController().navigate(R.id.action_settingFragment_to_profileFragment)
        }

        viewBinding.languageLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: languageLayout")
            findNavController().navigate(R.id.action_settingFragment_to_languageFragment2)
        }

        viewBinding.aboutLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: aboutLayout")
        }

        viewBinding.helpLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: helpLayout")
        }

        viewBinding.clearDataBaseLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: clearDataBaseLayout")

            val warningModel = WarningModel(
                dialogMessage = "Are you sure you want to clear all data? This action cannot be undone.",
                positiveButtonText = "Clear",
                negativeButtonText = "Cancel",
                dialogWarningErrorIconId = R.drawable.ic_warning
            )
            GenericDialog(
                warningModel = warningModel,
                onNegative = {
                    Log.d(TAG, "onViewCreated: Clear Data Cancelled")
                },
                onPositive = {
                    Log.d(TAG, "onViewCreated: Clear Data Confirmed")
                    mainViewModel.clearAllData()
                }
            ).show(parentFragmentManager, "GenericDialog")
        }

        viewBinding.analysisLayout.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: analysisLayout")
            findNavController().navigate(R.id.action_settingFragment_to_analysisFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: Back press disabled on this screen")
            findNavController().popBackStack()
        }

        viewBinding.versionET.text = BuildConfig.VERSION_NAME

    }

}
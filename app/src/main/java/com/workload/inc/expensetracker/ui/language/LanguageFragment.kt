package com.workload.inc.expensetracker.ui.language

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.data.languageList
import com.workload.inc.expensetracker.databinding.FragmentLanguageBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.VerticalSpaceItemDecoration
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel

class LanguageFragment : BaseFragment<FragmentLanguageBinding>() {

    private val TAG = "LanguageFragment"
    private var languageAdapter: LanguageAdapter? = null
    private var selectedLanguage: String? = null
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_language
    }

    override fun inflateViewBinding(): FragmentLanguageBinding {
        return FragmentLanguageBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        languageAdapter = LanguageAdapter(
            languages = languageList,
            onLanguageSelected = { languageModel, position ->
                Log.d(TAG, "Language selected : $languageModel")
                viewBinding.selectedLanguageTV.text = languageModel.languageName
                viewBinding.selectedLanguageRB.isChecked = true
                selectedLanguage = languageModel.languageCode
                languageAdapter?.notifyChange(position)
            }
        )

        viewBinding.languageRV.addItemDecoration(VerticalSpaceItemDecoration(16))
        viewBinding.languageRV.adapter = languageAdapter

        viewBinding.nextTV.setSafeOnClickListener {
            Log.d(TAG, "Next clicked")
            if (selectedLanguage != null) {

                val navController = findNavController()
                val previousDestinationId = navController.previousBackStackEntry?.destination?.id

                when (previousDestinationId) {
                    R.id.splashFragment -> {
                        onBoardingViewModel.setValue(
                            AppSharedPrefKeys.SELECTED_LANGUAGE,
                            selectedLanguage!!
                        )
                        navController.navigate(R.id.action_languageFragment_to_onBoardingFragment)
                    }

                    R.id.settingFragment -> {
                        navController.popBackStack()
                    }

                    else -> {
                        Log.d(
                            TAG,
                            "Previous destination unknown or not handled: $previousDestinationId"
                        )
                    }
                }

            } else {
                showToast("Please select language")
            }
        }

    }

}
package com.workload.inc.expensetracker.ui.splash

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentSplashBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.getValue

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val TAG = "SplashFragment"
    private val onBoardingViewModel: OnBoardingViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_splash
    }

    override fun inflateViewBinding(): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userIsRegistered = onBoardingViewModel.getBoolean(AppSharedPrefKeys.IS_REGISTERED)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            if (userIsRegistered) {
                Log.d(TAG, "User is registered, navigating to SignIn")
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            } else {
                Log.d(TAG, "User is not registered, navigating to Language Selection")
                findNavController().navigate(R.id.action_splashFragment_to_languageFragment)
            }
        }

    }

}
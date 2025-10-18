package com.workload.inc.expensetracker.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentProfileBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.ui.OnBoardingActivity
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import kotlin.getValue

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val TAG = "ProfileFragment"
    private val mainViewModel: MainViewModel by activityViewModels()
    private var savedName: String? = null
    private var savedEmail: String? = null
    private var savedNumber: String? = null

    override fun getResLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun inflateViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedName = mainViewModel.getValue(AppSharedPrefKeys.USER_NAME)
        savedEmail = mainViewModel.getValue(AppSharedPrefKeys.USER_EMAIL)
        savedNumber = mainViewModel.getValue(AppSharedPrefKeys.PHONE_NUMBER)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: Back press disabled on this screen")
            findNavController().popBackStack()
        }

        viewBinding.nameET.setText(savedName)
        viewBinding.emailET.setText(savedEmail)
        viewBinding.numberET.setText(savedNumber)

        viewBinding.logOutButton.setSafeOnClickListener {

            Log.d(TAG, "onViewCreated: Log out clicked")
            val onBoardingIntent = Intent(requireActivity(), OnBoardingActivity::class.java)
            onBoardingIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(onBoardingIntent)
            requireActivity().finish()

        }

    }

}
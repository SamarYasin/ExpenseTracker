package com.workload.inc.expensetracker.ui.profile

import android.os.Bundle
import android.view.View
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentProfileBinding

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val TAG = "ProfileFragment"

    override fun getResLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun inflateViewBinding(): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}
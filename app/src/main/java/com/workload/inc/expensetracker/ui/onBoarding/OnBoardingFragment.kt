package com.workload.inc.expensetracker.ui.onBoarding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentOnBoardingBinding
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.hideView
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showView
import com.workload.inc.expensetracker.viewmodel.OnBoardingViewModel
import kotlin.getValue

class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>() {

    private var onBoardingPagerAdapter: OnBoardingPagerAdapter? = null
    private val TAG = "OnBoardingFragment"
    private val onBoardingViewModel : OnBoardingViewModel by activityViewModels()
    private val layoutList: List<Int> = listOf(
        R.layout.on_boarding_first_page,
        R.layout.on_boarding_second_page,
        R.layout.on_boarding_third_page,
        R.layout.on_boarding_forth_page
    )
    private val activeDrawable = R.drawable.step_active
    private val inActiveDrawable = R.drawable.step_inactive

    override fun getResLayout(): Int {
        return R.layout.fragment_on_boarding
    }

    override fun inflateViewBinding(): FragmentOnBoardingBinding {
        return FragmentOnBoardingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onBoardingPagerAdapter = OnBoardingPagerAdapter(
            layoutIds = layoutList
        )

        viewBinding.viewPager.adapter = onBoardingPagerAdapter

        viewBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        Log.d(TAG, "onPageSelected: 0")
                        viewBinding.progressBar.showView()
                        viewBinding.swipeView.showView()
                        viewBinding.progressOne.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                activeDrawable
                            )
                        )
                        viewBinding.progressTwo.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                        viewBinding.progressThree.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                    }

                    1 -> {
                        Log.d(TAG, "onPageSelected: 1")
                        viewBinding.progressBar.showView()
                        viewBinding.swipeView.showView()
                        viewBinding.progressOne.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                        viewBinding.progressTwo.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                activeDrawable
                            )
                        )
                        viewBinding.progressThree.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                    }

                    2 -> {
                        Log.d(TAG, "onPageSelected: 2")
                        viewBinding.progressBar.showView()
                        viewBinding.swipeView.showView()
                        viewBinding.progressOne.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                        viewBinding.progressTwo.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                inActiveDrawable
                            )
                        )
                        viewBinding.progressThree.setImageDrawable(
                            ContextCompat.getDrawable(
                                requireContext(),
                                activeDrawable
                            )
                        )
                    }

                    3 -> {
                        Log.d(TAG, "onPageSelected: 3")
                        viewBinding.progressBar.hideView()
                        viewBinding.swipeView.hideView()

                        val recyclerView =
                            viewBinding.viewPager.getChildAt(0) as? RecyclerView
                        val holder =
                            recyclerView?.findViewHolderForAdapterPosition(position) as? OnBoardingPagerAdapter.PageViewHolder
                        val pageView = holder?.itemView

                        // Register button
                        pageView?.findViewById<View>(R.id.registerButton)?.setSafeOnClickListener {
                            Log.d(TAG, "onPageSelected: Register button clicked")
                            onBoardingViewModel.setBoolean(AppSharedPrefKeys.IS_REGISTERED, true)
                            findNavController().navigate(R.id.action_onBoardingFragment_to_signUpFragment)
                        }

                        // Sign In button/text
                        pageView?.findViewById<View>(R.id.signInTVBtn)?.setSafeOnClickListener {
                            Log.d(TAG, "onPageSelected: Sign In button clicked")
                            findNavController().navigate(R.id.action_onBoardingFragment_to_signInFragment)
                        }

                    }
                }
            }
        })

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            Log.d(TAG, "onViewCreated: Back press disabled on this screen")
            findNavController().popBackStack()
        }

    }

}
package com.workload.inc.expensetracker.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.databinding.FragmentHomeBinding
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntry
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.DateUtils.formatDateFromMillis
import com.workload.inc.expensetracker.utils.VerticalSpaceItemDecoration
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import org.eazegraph.lib.models.PieModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val TAG = "HomeFragment"
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
    private var expenseAdapter: ExpenseAdapter? = null
    private var formattedDate: String = ""

    override fun getResLayout(): Int {
        return R.layout.fragment_home
    }

    override fun inflateViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDateFormat = mainViewModel.getValue(AppSharedPrefKeys.DATE_FORMAT)
        if (selectedDateFormat.isNullOrEmpty()) {
            showToast("Date format not set.")
        } else {
            formattedDate = formatDateFromMillis(System.currentTimeMillis(), selectedDateFormat)
            // Entries for each expense category
            mainViewModel.getExpenseForToday(formattedDate)
            // Entries for Entire Day
            mainViewModel.getAllDailyExpenseEntries()
        }

        expenseAdapter = ExpenseAdapter(
            expenseList = listOf()
        )

        viewBinding.expenseRV.addItemDecoration(VerticalSpaceItemDecoration(16))
        viewBinding.expenseRV.adapter = expenseAdapter

        viewBinding.floatingActionButton.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: FAB Clicked")
            findNavController().navigate(R.id.action_homeFragment_to_addExpenseFragment)
        }

        viewBinding.settingIV.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: Settings Clicked")
            findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
        }

        mainViewModel.todayExpense.observe(viewLifecycleOwner) { expenseEntries ->
            if (expenseEntries.isEmpty()) {
                Log.d(TAG, "onViewCreated: Empty")
            } else {
                Log.d(TAG, "onViewCreated: Not Empty")
                expenseAdapter?.updateList(expenseEntries)
            }
        }

        mainViewModel.expense.observe(viewLifecycleOwner) { entries ->
            if (entries.isEmpty()) {
                Log.d(TAG, "onViewCreated: Empty")
            } else {
                Log.d(TAG, "onViewCreated: Not Empty")
                setUpPieChart(entries)
            }
        }

    }

    private fun setUpPieChart(entries: List<DailyExpenseEntry>) {
        viewBinding.pieChart.clearChart()

        entries.forEach { item ->

            if (item.residenceExpense.isNotEmpty()) {
                residenceExpense += item.residenceExpense.toInt()
            }
            if (item.foodExpense.isNotEmpty()) {
                foodExpense += item.foodExpense.toInt()
            }
            if (item.utilitiesExpense.isNotEmpty()) {
                utilitiesExpense += item.utilitiesExpense.toInt()
            }
            if (item.fuelExpense.isNotEmpty()) {
                fuelExpense += item.fuelExpense.toInt()
            }
            if (item.clothingExpense.isNotEmpty()) {
                clothingExpense += item.clothingExpense.toInt()
            }
            if (item.shoppingExpense.isNotEmpty()) {
                shoppingExpense += item.shoppingExpense.toInt()
            }
            if (item.giftsDonationsExpense.isNotEmpty()) {
                giftsDonationsExpense += item.giftsDonationsExpense.toInt()
            }
            if (item.travelExpense.isNotEmpty()) {
                travelExpense += item.travelExpense.toInt()
            }
            if (item.taxesExpense.isNotEmpty()) {
                taxesExpense += item.taxesExpense.toInt()
            }
            if (item.insuranceExpense.isNotEmpty()) {
                insuranceExpense += item.insuranceExpense.toInt()
            }
            if (item.entertainmentExpense.isNotEmpty()) {
                entertainmentExpense += item.entertainmentExpense.toInt()
            }
            if (item.healthExpense.isNotEmpty()) {
                healthExpense += item.healthExpense.toInt()
            }
            if (item.personalCareExpense.isNotEmpty()) {
                personalCareExpense += item.personalCareExpense.toInt()
            }
            if (item.educationExpense.isNotEmpty()) {
                educationExpense += item.educationExpense.toInt()
            }
            if (item.othersExpense.isNotEmpty()) {
                othersExpense += item.othersExpense.toInt()
            }

        }

        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Residence",
                1200f,
                Color.parseColor(
                    "#29B6F6"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Food",
                500f,
                Color.parseColor(
                    "#FFA726"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Utilities",
                250f,
                Color.parseColor(
                    "#FF7043"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Fuel",
                100f,
                Color.parseColor(
                    "#AB47BC"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Clothing",
                80f,
                Color.parseColor(
                    "#8D6E63"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Shopping",
                150f,
                Color.parseColor(
                    "#42A5F5"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Gifts/Donations",
                60f,
                Color.parseColor(
                    "#FFCA28"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Travel",
                200f,
                Color.parseColor(
                    "#66BB6A"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Taxes",
                90f,
                Color.parseColor(
                    "#9CCC65"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Insurance",
                70f,
                Color.parseColor(
                    "#D4AC0D"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Entertainment",
                130f,
                Color.parseColor(
                    "#EF5350"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Health",
                300f,
                Color.parseColor(
                    "#A569BD"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Personal Care",
                40f,
                Color.parseColor(
                    "#2980B9"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Education",
                120f,
                Color.parseColor(
                    "#34495E"
                )
            )
        )
        viewBinding.pieChart.addPieSlice(
            PieModel(
                "Others",
                50f,
                Color.parseColor(
                    "#2ECC71"
                )
            )
        )

        viewBinding.pieChart.startAnimation()
    }

}
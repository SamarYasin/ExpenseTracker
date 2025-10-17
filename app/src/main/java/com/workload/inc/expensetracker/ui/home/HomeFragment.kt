package com.workload.inc.expensetracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.bottomSheet.BudgetBottomSheet
import com.workload.inc.expensetracker.bottomSheet.CurrencyBottomSheet
import com.workload.inc.expensetracker.bottomSheet.IncomeBottomSheet
import com.workload.inc.expensetracker.databinding.FragmentHomeBinding
import com.workload.inc.expensetracker.localDb.room.DailyExpenseEntry
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.DateUtils.formatDateFromMillis
import com.workload.inc.expensetracker.utils.VerticalSpaceItemDecoration
import com.workload.inc.expensetracker.utils.goneView
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.utils.showView
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import org.eazegraph.lib.models.PieModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val TAG = "HomeFragment"
    private var isFabOpen = false
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

        viewBinding.fabMain.setSafeOnClickListener {
            if (isFabOpen) {
                // Hide FABs
                viewBinding.budgetFAB.animate().translationY(0f).alpha(0f).withEndAction {
                    viewBinding.budgetFAB.goneView()
                }
                viewBinding.incomeFAB.animate().translationY(0f).alpha(0f).withEndAction {
                    viewBinding.incomeFAB.goneView()
                }
                viewBinding.currencyFAB.animate().translationY(0f).alpha(0f).withEndAction {
                    viewBinding.currencyFAB.goneView()
                }
                viewBinding.expenseFAB.animate().translationY(0f).alpha(0f).withEndAction {
                    viewBinding.expenseFAB.goneView()
                }
            } else {
                // Show FABs
                viewBinding.budgetFAB.showView()
                viewBinding.incomeFAB.showView()
                viewBinding.currencyFAB.showView()
                viewBinding.expenseFAB.showView()
                viewBinding.budgetFAB.animate().translationY(-300f).alpha(1f)
                viewBinding.budgetFAB.animate().translationX(-50f).alpha(1f)
                viewBinding.incomeFAB.animate().translationY(-450f).alpha(1f)
                viewBinding.incomeFAB.animate().translationX(-50f).alpha(1f)
                viewBinding.currencyFAB.animate().translationY(-600f).alpha(1f)
                viewBinding.currencyFAB.animate().translationX(-50f).alpha(1f)
                viewBinding.expenseFAB.animate().translationY(-750f).alpha(1f)
                viewBinding.expenseFAB.animate().translationX(-50f).alpha(1f)
            }
            isFabOpen = !isFabOpen
        }

        viewBinding.budgetFAB.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: Budget Clicked")
            BudgetBottomSheet(
                onAllowedBudget = { allowedBudget ->
                    Log.d(TAG, "onViewCreated: $allowedBudget")

                }
            ).show(
                parentFragmentManager,
                "PasswordUpdateBottomSheet"
            )
        }

        viewBinding.incomeFAB.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: Income Clicked")
            IncomeBottomSheet(
                onSetIncome = { incomeAmount ->
                    Log.d(TAG, "onViewCreated: $incomeAmount")

                }
            ).show(
                parentFragmentManager,
                "PasswordUpdateBottomSheet"
            )
        }

        viewBinding.currencyFAB.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: Currency Clicked")
            CurrencyBottomSheet(
                onCurrencySelected = { selectedCurrency ->
                    Log.d(TAG, "onViewCreated: $selectedCurrency")

                }
            ).show(
                parentFragmentManager,
                "PasswordUpdateBottomSheet"
            )
        }

        viewBinding.expenseFAB.setSafeOnClickListener {
            Log.d(TAG, "onViewCreated: Expense Clicked")
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

        with(viewBinding) {

            pieChart.clearChart()

            pieChart.addPieSlice(
                PieModel(
                    "Residence",
                    1200f,
                    "#29B6F6".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Food",
                    500f,
                    "#FFA726".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Utilities",
                    250f,
                    "#FF7043".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Fuel",
                    100f,
                    "#AB47BC".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Clothing",
                    80f,
                    "#8D6E63".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Shopping",
                    150f,
                    "#42A5F5".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Gifts/Donations",
                    60f,
                    "#FFCA28".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Travel",
                    200f,
                    "#66BB6A".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Taxes",
                    90f,
                    "#9CCC65".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Insurance",
                    70f,
                    "#D4AC0D".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Entertainment",
                    130f,
                    "#EF5350".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Health",
                    300f,
                    "#A569BD".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Personal Care",
                    40f,
                    "#2980B9".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Education",
                    120f,
                    "#34495E".toColorInt()
                )
            )
            pieChart.addPieSlice(
                PieModel(
                    "Others",
                    50f,
                    "#2ECC71".toColorInt()
                )
            )

            pieChart.startAnimation()

        }

    }

}
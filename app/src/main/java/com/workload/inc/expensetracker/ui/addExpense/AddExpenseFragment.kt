package com.workload.inc.expensetracker.ui.addExpense

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.base.BaseFragment
import com.workload.inc.expensetracker.bottomSheet.InitialSettingBottomSheet
import com.workload.inc.expensetracker.data.expenseNameList
import com.workload.inc.expensetracker.databinding.FragmentAddExpenseBinding
import com.workload.inc.expensetracker.localDb.room.ExpenseEntryModel
import com.workload.inc.expensetracker.localDb.room.UserFinanceModel
import com.workload.inc.expensetracker.localDb.sharedPref.AppSharedPrefKeys
import com.workload.inc.expensetracker.utils.CurrencyUtil
import com.workload.inc.expensetracker.utils.CurrencyUtil.withCurrency
import com.workload.inc.expensetracker.utils.DateUtils.formatDateFromMillis
import com.workload.inc.expensetracker.utils.DateUtils.formatTime12HourFromMillis
import com.workload.inc.expensetracker.utils.setSafeOnClickListener
import com.workload.inc.expensetracker.utils.showToast
import com.workload.inc.expensetracker.viewmodel.MainViewModel

class AddExpenseFragment : BaseFragment<FragmentAddExpenseBinding>() {

    private val TAG = "AddExpenseFragment"
    private var selectedExpense: String = ""
    private var formattedDate: String = ""
    private var savedFinancialSituation : UserFinanceModel? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun getResLayout(): Int {
        return R.layout.fragment_add_expense
    }

    override fun inflateViewBinding(): FragmentAddExpenseBinding {
        return FragmentAddExpenseBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            expenseNameList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.expenseSpinner.adapter = adapter

        viewBinding.expenseSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedExpense = expenseNameList[position]
                    Log.d(TAG, "Selected Expense: $selectedExpense")
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Log.d(TAG, "No Expense Type Selected")
                    showToast("Please select an expense type")
                }
            }

        val selectedDateFormat = mainViewModel.getValue(AppSharedPrefKeys.DATE_FORMAT)
        if (selectedDateFormat.isNullOrEmpty()) {
            showToast("Date format not set.")
        } else {
            formattedDate = formatDateFromMillis(System.currentTimeMillis(), selectedDateFormat)
            viewBinding.selectedDateFormatTV.text = formattedDate
        }

        viewBinding.addExpenseButton.setSafeOnClickListener {

            if (selectedExpense.isEmpty()) {
                showToast("Please select an expense type")
                return@setSafeOnClickListener
            }

            if (viewBinding.expenseDetailET.text.isNullOrEmpty()) {
                showToast("Please enter expense details")
                return@setSafeOnClickListener
            }

            if (viewBinding.amountET.text.isNullOrEmpty()) {
                showToast("Please enter amount")
                return@setSafeOnClickListener
            }

            val model = ExpenseEntryModel(
                expenseType = selectedExpense,
                expenseDetail = viewBinding.expenseDetailET.text.toString(),
                expenseAmount = viewBinding.amountET.text.toString(),
                date = formatDateFromMillis(System.currentTimeMillis(), selectedDateFormat!!),
                time = formatTime12HourFromMillis(),
            )

            val updatedFinancialSituation = UserFinanceModel(
                id = savedFinancialSituation?.id ?: 1,
                totalIncome = savedFinancialSituation?.totalIncome ?: 0,
                totalExpense = savedFinancialSituation?.totalExpense?.plus(
                    viewBinding.amountET.text.toString().toInt()
                ) ?: 0,
                balance = savedFinancialSituation?.balance?.minus(
                    viewBinding.amountET.text.toString().toInt()
                ) ?: 0,
                budget = savedFinancialSituation?.budget ?: 0
            )

            Log.d(TAG, "Expense Model: $model")
            Log.d(TAG, "Updated Financial Situation: $updatedFinancialSituation")

            mainViewModel.addExpense(
                expenseEntry = model,
                formattedDate = formattedDate
            )

            mainViewModel.insertOrUpdateUserFinance(updatedFinancialSituation)

            mainViewModel.getAllDailyExpenseEntries()

            showToast("Expense Added Successfully")
            findNavController().popBackStack()

        }

        mainViewModel.userFinancialSituation.observe(viewLifecycleOwner) { userFinanceModel ->
            Log.d(TAG, "onViewCreated: $userFinanceModel")

            savedFinancialSituation = userFinanceModel

        }

    }

}
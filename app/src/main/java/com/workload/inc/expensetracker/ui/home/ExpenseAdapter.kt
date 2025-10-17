package com.workload.inc.expensetracker.ui.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.databinding.ExpenseItemBinding
import com.workload.inc.expensetracker.localDb.room.ExpenseEntry

class ExpenseAdapter(
    private var expenseList: List<ExpenseEntry>
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(val binding: ExpenseItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        val binding = ExpenseItemBinding.inflate(
            LayoutInflater.from(
                parent.context
            ), parent, false
        )
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExpenseViewHolder,
        position: Int
    ) {
        val expense = expenseList[position]
        val context = holder.binding.root.context

        val expenseIcon = AppCompatResources.getDrawable(context, R.drawable.ic_expense)
        holder.binding.expenseIV.setImageDrawable(getExpenseIcon(context, expense.expenseType))
        holder.binding.amountDirectionIV.setImageDrawable(expenseIcon)

        holder.binding.expenseCategoryNameTV.text = expense.expenseType
        holder.binding.expenseAmountTV.text = expense.expenseAmount.toString()
        holder.binding.expenseNameTV.text = expense.expenseDetail
    }

    private fun getExpenseIcon(
        context: Context,
        expenseType: String
    ): Drawable? {
        return when (expenseType) {
            "Residence" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_rent
            )

            "Food" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_food
            )

            "Utilities" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_utilities
            )

            "Fuel" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_fuel
            )

            "Clothing" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_clothes
            )

            "Shopping" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_shopping
            )

            "Gifts/Donations" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_gift
            )

            "Travel" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_transportation
            )

            "Taxes" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_tax
            )

            "Insurance" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_insurance
            )

            "Entertainment" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_entertainment
            )

            "Health" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_health
            )

            "Personal Care" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_persoanl_care
            )

            "Education" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_education
            )

            "Others" -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_other
            )

            else -> AppCompatResources.getDrawable(
                context,
                R.drawable.ic_other
            )
        }
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    fun updateList(newExpenseList: List<ExpenseEntry>) {
        expenseList = newExpenseList
        notifyDataSetChanged()
    }

}
package com.workload.inc.expensetracker.ui.onBoarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class OnBoardingPagerAdapter(
    private val layoutIds: List<Int>
) : RecyclerView.Adapter<OnBoardingPagerAdapter.PageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        when (position) {
            0 -> {
                // First page specific logic (if any)
            }

            1 -> {
                // Second page specific logic (if any)
            }

            2 -> {
                // Third page specific logic (if any)
            }

            3 -> {
                // Fourth page specific logic (if any)
            }

            else -> {
                // Default case (if any)
            }
        }
    }

    override fun getItemCount(): Int = layoutIds.size

    override fun getItemViewType(position: Int): Int = layoutIds[position]

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
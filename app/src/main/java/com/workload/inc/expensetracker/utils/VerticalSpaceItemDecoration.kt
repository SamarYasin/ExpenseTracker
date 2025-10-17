package com.workload.inc.expensetracker.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalSpaceItemDecoration(
    private val verticalSpaceHeight: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // Add top margin only for items except the first one
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = verticalSpaceHeight
        }
    }
}
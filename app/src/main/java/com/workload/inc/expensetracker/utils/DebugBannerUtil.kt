package com.workload.inc.expensetracker.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.workload.inc.expensetracker.R

object DebugBannerUtil {
    fun injectBanner(activity: Activity) {
        val decorView = activity.window?.decorView as? ViewGroup ?: return
        val bannerView = LayoutInflater.from(activity)
            .inflate(R.layout.view_debug_banner, decorView, false)
        decorView.addView(bannerView)
    }
}
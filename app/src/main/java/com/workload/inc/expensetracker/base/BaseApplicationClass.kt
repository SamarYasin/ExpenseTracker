package com.workload.inc.expensetracker.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import com.workload.inc.expensetracker.BuildConfig
import com.workload.inc.expensetracker.utils.DebugBannerUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplicationClass : Application() {

    private val TAG = "BaseApplicationClass"

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
        if (BuildConfig.DEBUG) {
            registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    Log.d(TAG, "onActivityCreated: ")
                    DebugBannerUtil.injectBanner(activity)
                }
                override fun onActivityStarted(activity: Activity) {
                    Log.d(TAG, "onActivityStarted: ")
                }
                override fun onActivityResumed(activity: Activity) {
                    Log.d(TAG, "onActivityResumed: ")
                }
                override fun onActivityPaused(activity: Activity) {
                    Log.d(TAG, "onActivityPaused: ")
                }
                override fun onActivityStopped(activity: Activity) {
                    Log.d(TAG, "onActivityStopped: ")
                }
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                    Log.d(TAG, "onActivitySaveInstanceState: ")
                }
                override fun onActivityDestroyed(activity: Activity) {
                    Log.d(TAG, "onActivityDestroyed: ")
                }
            })
        }
    }

}
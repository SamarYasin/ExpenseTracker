package com.workload.inc.expensetracker.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.workload.inc.expensetracker.R
import com.workload.inc.expensetracker.databinding.ActivityMainBinding
import com.workload.inc.expensetracker.utils.AlarmScheduler
import com.workload.inc.expensetracker.utils.setFullScreen
import com.workload.inc.expensetracker.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.dailyExpenseEntries.observe(this) { expenseEntries ->



        }

        AlarmScheduler.scheduleDailyAlarm(this)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainFragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

    }

}
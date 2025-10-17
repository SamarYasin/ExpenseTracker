package com.workload.inc.expensetracker.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var viewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = inflateViewBinding()
        setContentView(viewBinding.root)
    }

    abstract fun inflateViewBinding(): VB

    @LayoutRes
    abstract fun getResLayout(): Int

}
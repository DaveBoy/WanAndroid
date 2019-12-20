package com.daveboy.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        afterSetView()
        initView()
        initListener()
        initData()
    }
    open fun afterSetView (){}
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initListener()
    abstract fun initData()
    fun showProgress(){

    }
    fun dismissProgress(){

    }
}
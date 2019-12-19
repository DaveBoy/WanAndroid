package com.daveboy.wanandroid.di

import com.daveboy.wanandroid.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule= module {
    viewModel{ LoginViewModel() }
}
val diModules= listOf(fragmentModule)
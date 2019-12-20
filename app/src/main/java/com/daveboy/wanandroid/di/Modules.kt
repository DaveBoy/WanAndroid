package com.daveboy.wanandroid.di

import com.daveboy.wanandroid.http.ApiService
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.ui.login.LoginRepository
import com.daveboy.wanandroid.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val fragmentModule= module {
    viewModel{ LoginViewModel() }
}
val repositorys= module{
    single { LoginRepository() }
}
val netModules= module {
    single { RetrofitManager.service }
}
val diModules= listOf(fragmentModule,repositorys)
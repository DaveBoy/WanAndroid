package com.daveboy.wanandroid.di

import com.daveboy.wanandroid.http.ApiService
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.ui.login.LoginRepository
import com.daveboy.wanandroid.ui.login.LoginViewModel
import com.daveboy.wanandroid.ui.main.index.IndexFragment
import com.daveboy.wanandroid.ui.main.index.IndexRepository
import com.daveboy.wanandroid.ui.project.ProjectFragment
import com.daveboy.wanandroid.ui.system.SystemFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val fragmentModule= module {
    viewModel{ LoginViewModel() }
}
val repositorys= module{
    single { LoginRepository() }
    single { IndexRepository() }
}
val netModules= module {
    single { RetrofitManager.service }
}
val fragments= module {
    fragment { IndexFragment() }
    fragment { ProjectFragment() }
    fragment { SystemFragment() }
}
val diModules= listOf(fragmentModule,repositorys,fragments)
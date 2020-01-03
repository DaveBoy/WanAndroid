package com.daveboy.wanandroid.di

import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.ui.login.LoginRepository
import com.daveboy.wanandroid.ui.login.LoginViewModel
import com.daveboy.wanandroid.ui.main.index.IndexFragment
import com.daveboy.wanandroid.ui.main.index.IndexRepository
import com.daveboy.wanandroid.ui.main.search.fragment.SearchRepository
import com.daveboy.wanandroid.ui.main.search.fragment.SearchResultRepository
import com.daveboy.wanandroid.ui.mine.MineFragment
import com.daveboy.wanandroid.ui.mine.MineRepository
import com.daveboy.wanandroid.ui.project.ProjectFragment
import com.daveboy.wanandroid.ui.project.ProjectRepository
import com.daveboy.wanandroid.ui.project.content.ContentRepository
import com.daveboy.wanandroid.ui.system.SystemRepository
import com.daveboy.wanandroid.ui.system.SystemWrapperFragment
import com.daveboy.wanandroid.ui.system.content.SystemContentRepository
import com.daveboy.wanandroid.ui.system.navigation.NavigationRepository
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule= module {
    viewModel{ LoginViewModel() }
}
val repositorys= module{
    single { LoginRepository() }
    single { IndexRepository() }
    single { ProjectRepository() }
    single { ContentRepository() }
    single { SystemRepository() }
    single { NavigationRepository() }
    single { SearchResultRepository() }
    single { SearchRepository() }
    single { SystemContentRepository() }
    single { MineRepository() }
}
val netModules= module {
    single { RetrofitManager.service }
}
val fragments= module {
    fragment { IndexFragment() }
    fragment { ProjectFragment() }
    fragment { SystemWrapperFragment() }
    fragment { MineFragment() }
}
val diModules= listOf(fragmentModule,repositorys,fragments,netModules)
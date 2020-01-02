package com.daveboy.wanandroid.ui.system.navigation

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.NavigationModel
import com.daveboy.wanandroid.entity.SystemModel
import org.koin.core.inject

class NavigationViewModel: BaseViewModel() {
    val navigationList =MutableLiveData<ViewState<List<NavigationModel>>>()
    private val repository: NavigationRepository by inject()

    fun getSystemList(){
        launchRequest({repository.getNavigationList()},navigationList)
    }

}
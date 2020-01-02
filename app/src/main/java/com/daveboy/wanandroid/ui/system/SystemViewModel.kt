package com.daveboy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.SystemModel
import org.koin.core.inject

class SystemViewModel: BaseViewModel() {
    val systemList =MutableLiveData<ViewState<List<SystemModel>>>()
    private val repository: SystemRepository by inject()

    fun getSystemList(){
        launchRequest({repository.getSystemList()},systemList)
    }

}
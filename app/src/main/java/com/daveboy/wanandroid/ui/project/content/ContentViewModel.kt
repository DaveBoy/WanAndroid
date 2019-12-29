package com.daveboy.wanandroid.ui.project.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.*
import kotlinx.coroutines.launch
import org.koin.core.inject

class ContentViewModel: BaseViewModel() {
    val tabProjectResponse =MutableLiveData<ViewState<TabProjectResponse>>()
    private val repository: ContentRepository by inject()
    var page= 1 //项目列表数据从1开始

    var cid: Int? = null

    fun getTabProjectResponse(refresh:Boolean){
        if(refresh) page=1
        launchRequest({repository.getTabProject(page,cid!!)},tabProjectResponse,success = {page++})
    }
}
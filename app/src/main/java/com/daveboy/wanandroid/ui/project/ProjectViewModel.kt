package com.daveboy.wanandroid.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.ProjectTab
import com.daveboy.wanandroid.ui.main.index.IndexRepository
import kotlinx.coroutines.launch
import org.koin.core.inject

class ProjectViewModel: BaseViewModel() {
    val tabList =MutableLiveData<ViewState<List<ProjectTab>>>()
    private val repository: ProjectRepository by inject()

    fun getTabList(){
        launchRequest({repository.getTabList()},tabList)
    }
}
package com.daveboy.wanandroid.ui.project

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.entity.ProjectTab
import kotlinx.coroutines.launch

class ProjectViewModel: BaseViewModel() {
    val tabList =MutableLiveData<List<ProjectTab>>()
    private val repository= ProjectRepository()
    val errorMsg=MutableLiveData<String>()

    fun getTabList(){
        viewModelScope.launch {
            runCatching {
                repository.getTabList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    tabList.value=it.data
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }
}
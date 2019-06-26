package com.daveboy.wanandroid.ui.project.content

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.*
import com.daveboy.wanandroid.ui.main.index.IndexRepository
import com.daveboy.wanandroid.ui.project.ProjectRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContentViewModel: BaseViewModel() {
    val tabProjectResponse =MutableLiveData<TabProjectResponse>()
    private val repository= ContentRepository()
    val errorMsg=MutableLiveData<String>()
    val page= MutableLiveData(1)
    val cid= MutableLiveData<Int>()

    fun getTabProjectResponse(){
        viewModelScope.launch {
            runCatching {
                repository.getTabProject(page.value?:1,cid.value!!)
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    page.value=(page.value?:1)+1
                    tabProjectResponse.value=it.data
                    //repository.insertTab(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }
}
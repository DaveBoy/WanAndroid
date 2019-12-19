package com.daveboy.wanandroid.ui.system

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.entity.SystemModel
import kotlinx.coroutines.launch

class SystemViewModel: BaseViewModel() {
    val systemList =MutableLiveData<List<SystemModel>>()
    private val repository= SystemRepository()
    val errorMsg=MutableLiveData<String>()

    fun getSystemList(){
        viewModelScope.launch {
            runCatching {
                repository.getSystemList()
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    LogUtils.i(it.data)
                    systemList.value=it.data
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }
    }

}
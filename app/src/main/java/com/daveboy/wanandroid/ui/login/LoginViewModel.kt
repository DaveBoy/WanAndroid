package com.daveboy.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.common.SP_COOKIE
import com.daveboy.common.Util.getString
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {
    val loginState=MutableLiveData(getString(SP_COOKIE).isNullOrBlank().not())
    val errorMsg=MutableLiveData<String>()
    private val repository= LoginRepository()

    fun login(username:String,password:String){
        viewModelScope.launch {
            runCatching {
                repository.login(username, password)
            }.onSuccess {
                if(it.errorCode!=0){
                    errorMsg.value=it.errorMsg
                }else {
                    repository.insertLoginUser(it.data)
                    loginState.value=true
                    LogUtils.i(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                errorMsg.value="网络请求失败${it.message}"
            }

        }

    }
}
package com.daveboy.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.wanandroid.database.BoxManager
import com.daveboy.wanandroid.database.UserModel
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import kotlinx.coroutines.launch

class LoginViewModel: BaseViewModel() {
    val loginUser=MutableLiveData<LoginUser>()
    private val repository=LoginRepository()
    private lateinit var box : Box<UserModel>
    fun login(username:String,password:String){
        viewModelScope.launch {
            runCatching {
                repository.login(username, password)
            }.onSuccess {
                if(it.errorCode!=0){
                    ToastUtils.showShort(it.errorMsg)
                }else {
                    if(!::box.isInitialized) {
                        box = BoxManager.boxStore.boxFor()
                    }
                    box.removeAll()
                    box.put(it.data)
                    LogUtils.i(it.data)
                }
            }.onFailure {
                it.printStackTrace()
                ToastUtils.showShort("登陆失败${it.message}")
            }

        }

    }
}
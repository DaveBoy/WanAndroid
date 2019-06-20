package com.daveboy.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.daveboy.wanandroid.base.BaseViewModel

class LoginViewModel:BaseViewModel() {
    val loginUser=MutableLiveData<LoginUser>()
    private val repository=LoginRepository()
    fun login(username:String,password:String){
        launch {
            val respose = repository.login(username, password)
            executeResponse(respose,{ },{ loginUser.value=respose.data })
        }

    }
}
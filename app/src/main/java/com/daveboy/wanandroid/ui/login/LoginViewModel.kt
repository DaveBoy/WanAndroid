package com.daveboy.wanandroid.ui.login

import androidx.lifecycle.MutableLiveData
import com.daveboy.base.BaseViewModel
import com.daveboy.base.core.ViewState
import com.daveboy.base.util.launchRequest
import com.daveboy.wanandroid.entity.UserModel
import org.koin.core.inject

class LoginViewModel : BaseViewModel() {
    private val repository: LoginRepository by inject()

    val uiState = MutableLiveData<LoginUiModel>()
    val requestState = MutableLiveData<ViewState<UserModel>>()


    fun login(username: String, password: String) {
        launchRequest({
            repository.login(username, password)
        }, requestState)
    }
    fun loginInputChange(username: String,password: String){
        uiState.value= LoginUiModel(loginEnable = username.isNotBlank()&&password.isNotBlank())
    }
}
data class LoginUiModel(val loginEnable:Boolean=false)


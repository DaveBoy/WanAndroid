package com.daveboy.wanandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.daveboy.base.BaseViewModel
import com.daveboy.common.SP_COOKIE
import com.daveboy.common.util.getString
import com.daveboy.wanandroid.entity.UserModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    private val _loginState = MutableLiveData<LoginUiModel>()
    val loginState: LiveData<LoginUiModel>
        get() {
            return _loginState
        }
    private val repository = LoginRepository()

    fun login(username: String, password: String) {
        runOnUi({
            emitState(showProgress = true)
            repository.login(username, password)
        }, {

        }, {
            emitState(showError = it.message)
        })
    }

    private fun emitState(
        showProgress: Boolean = false,
        showError: String? = null,
        showSuccess: UserModel? = null,
        enableLoginButton: Boolean = false,
        needLogin: Boolean = false
    ) {
        val model = LoginUiModel(showProgress, showError, showSuccess, enableLoginButton, needLogin)
        _loginState.value = model
    }
}

data class LoginUiModel(
    val showProgress: Boolean,
    val showError: String?,
    val showSuccess: UserModel?,
    val enableLoginButton: Boolean,
    val needLogin: Boolean
)
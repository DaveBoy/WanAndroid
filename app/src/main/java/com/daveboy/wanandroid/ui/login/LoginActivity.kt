package com.daveboy.wanandroid.ui.login

import android.view.View
import androidx.lifecycle.Observer
import com.daveboy.base.BaseVMActivity
import com.daveboy.base.util.parseState
import com.daveboy.common.listener.textWatcher
import com.daveboy.common.util.startActivityExt
import com.daveboy.common.util.textStr
import com.daveboy.common.util.toast
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {
    override fun getLayoutId() = R.layout.activity_login
    override fun startObserve() {
        viewModel.apply {
            requestState.observe(this@LoginActivity, Observer {
                parseState(it,{
                    startActivityExt<MainActivity>()
                    finish()
                },{
                    toast(text=it.summary)
                })
            })
            uiState.observe(this@LoginActivity, Observer { login.isEnabled=it.loginEnable })
        }
    }

    override fun initView() {}
    override fun initData() {}

    override fun initListener() {
        login.setOnClickListener(onclickListener)
        login.textWatcher { afterTextChanged { viewModel.loginInputChange(username.textStr,password.textStr) } }
    }

    private fun login() {
        viewModel.login(username.text.toString(), password.text.toString())
    }

    private val onclickListener = View.OnClickListener {
        when (it.id) {
            R.id.login -> login()
        }
    }

}

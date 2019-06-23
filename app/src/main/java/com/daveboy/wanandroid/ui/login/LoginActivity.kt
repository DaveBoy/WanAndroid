package com.daveboy.wanandroid.ui.login

import android.content.Intent
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.daveboy.base.BaseVMActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {
    override fun getLayoutId()=R.layout.activity_login
    override fun providerVMClass() = LoginViewModel::class.java

    override fun initView() {
    }

    override fun initListener() {
        login.setOnClickListener {
            viewModel.login(username.text.toString(), password.text.toString())
        }
    }

    override fun initData() {
    }

    override fun createObserver() {
        viewModel.apply {
            loginState.observe(this@LoginActivity, Observer {
                startActivity(Intent().apply {
                    setClass(this@LoginActivity, MainActivity::class.java)
                    this@LoginActivity.finish()
                })
            })
            errorMsg.observe(this@LoginActivity, Observer {
                ToastUtils.showShort(it)
            })
        }
    }

}

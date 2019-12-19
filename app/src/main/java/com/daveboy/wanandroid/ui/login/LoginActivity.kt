package com.daveboy.wanandroid.ui.login

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.daveboy.base.BaseVMActivity
import com.daveboy.wanandroid.R
import com.daveboy.wanandroid.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseVMActivity<LoginViewModel>() {
    override fun getLayoutId() = R.layout.activity_login
    override fun startObserve() {
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

    override fun initView() {}
    override fun initData() {}

    override fun initListener() {
        login.setOnClickListener(onclickListener)
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

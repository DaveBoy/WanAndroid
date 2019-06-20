package com.daveboy.wanandroid.ui.login

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.daveboy.wanandroid.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        initViewModel()
    }

    private fun initViewModel() {
        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel::class.java)
        loginViewModel.loginUser.observe(this, Observer {
            /**
             * 登录成功后
             */
            Log.i("login","login sucess")
        })
        login.setOnClickListener {
            loginViewModel.login(username.text.toString(),password.text.toString())
        }
    }
}

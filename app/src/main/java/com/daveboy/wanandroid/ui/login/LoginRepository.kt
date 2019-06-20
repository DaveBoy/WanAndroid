package com.daveboy.wanandroid.ui.login

import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse

class LoginRepository {
    suspend fun login(username:String,password:String): WanResponse<LoginUser> {
        return RetrofitManager.service.login(username,password)
    }
}
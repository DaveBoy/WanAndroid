package com.daveboy.wanandroid.ui.login

import com.daveboy.wanandroid.entity.UserModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.base.BaseResponse
import com.daveboy.base.IRepository
import com.daveboy.wanandroid.http.ApiService
import org.koin.core.KoinComponent
import org.koin.core.bind
import org.koin.core.inject
import org.koin.experimental.property.inject

class LoginRepository:IRepository {
    val service:ApiService by inject()

    suspend fun login(username:String,password:String): BaseResponse<UserModel> {
        return service.login(username,password)
    }
}
package com.daveboy.wanandroid.ui.login

import com.daveboy.wanandroid.database.BoxManager
import com.daveboy.wanandroid.database.UserModel
import com.daveboy.wanandroid.http.RetrofitManager
import com.daveboy.wanandroid.http.WanResponse
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class LoginRepository {
    private lateinit var box : Box<UserModel>
    suspend fun login(username:String,password:String): WanResponse<UserModel> {
        return RetrofitManager.service.login(username,password)
    }
    fun insertLoginUser(data: UserModel) {
        if(!::box.isInitialized) {
            box = BoxManager.boxStore.boxFor()
        }
        box.removeAll()
        box.put(data)
    }
}
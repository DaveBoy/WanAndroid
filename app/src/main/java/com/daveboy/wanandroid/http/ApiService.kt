package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.ui.login.LoginUser
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.QueryName

interface ApiService {
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    suspend fun login(@Field("username")username:String, @Field("password")password:String): WanResponse<LoginUser>
}
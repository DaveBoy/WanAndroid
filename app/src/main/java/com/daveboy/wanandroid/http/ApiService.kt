package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.database.UserModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    suspend fun login(@Field("username")username:String, @Field("password")password:String): WanResponse<UserModel>
}
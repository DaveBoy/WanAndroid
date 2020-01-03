package com.daveboy.wanandroid.http

import android.util.Log
import com.blankj.utilcode.util.LogUtils
import com.daveboy.common.SP_COOKIE
import com.daveboy.common.util.Preference
import com.daveboy.common.util.getString
import com.daveboy.common.util.put
import com.daveboy.wanandroid.constant.TOKEN_KEY
import com.daveboy.wanandroid.constant.USERNAME_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private var token by Preference(TOKEN_KEY,"")

    val service by lazy {
        Retrofit
            .Builder()
            .client(getOkHttpClient())
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }


   private fun getOkHttpClient():OkHttpClient{
       return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.i("RetrofitLog",it)
            }).apply {
                level=HttpLoggingInterceptor.Level.BODY
            })
             .addInterceptor {
                val requestUrl=it.request().url().toString()
                 if(!requestUrl.contains(Urls.LOGIN)){
                    it.proceed(it.request().newBuilder().addHeader("Cookie", token).build())
                 }else{
                     val response = it.proceed(it.request().newBuilder().build())
                     val cookie=response.headers("set-cookie")
                     token=cookie.toString()
                     LogUtils.i("cookie:$cookie")
                     response
                 }
             }
            .build()
    }
}
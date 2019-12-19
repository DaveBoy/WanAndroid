package com.daveboy.wanandroid.http

import com.blankj.utilcode.util.LogUtils
import com.daveboy.common.SP_COOKIE
import com.daveboy.common.util.getString
import com.daveboy.common.util.put
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
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
            .addInterceptor(HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            })
             .addInterceptor {
                 LogUtils.i("请求地址：${it.request().url()}")
                val requestUrl=it.request().url().toString()
                 if(!requestUrl.contains(Urls.LOGIN)){
                    it.proceed(it.request().newBuilder().addHeader("Cookie", getString(SP_COOKIE)?:"").build())
                 }else{
                     val response = it.proceed(it.request().newBuilder().build())
                     val cookie=response.headers("set-cookie")
                     put(SP_COOKIE,cookie.toString())
                     LogUtils.i("cookie:$cookie")
                     response
                 }
             }
            .build()
    }
}
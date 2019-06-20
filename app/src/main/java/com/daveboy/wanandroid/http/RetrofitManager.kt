package com.daveboy.wanandroid.http

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
            /* .addInterceptor {
                 if(getString(SP_COOKIE)==null) {
                     //获取
                     val headers = it.proceed(it.request()).headers("Set-Cookie")
                     LogUtils.v ("Get-Cookie:$headers")
                     put(SP_COOKIE,headers.toString())
                 }
                 //设置
                 it.proceed(it.request().newBuilder().addHeader("Cookie", kv.decodeString(COOKIE_KEY)).build())
             }*/
            .build()
    }
}
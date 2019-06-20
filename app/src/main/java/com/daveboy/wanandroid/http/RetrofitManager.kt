package com.daveboy.wanandroid.http

import com.daveboy.wanandroid.constant.COOKIE_KEY
import com.daveboy.wanandroid.util.logi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {
    private val kv=MMKV.defaultMMKV()
    private val okHttpClient:OkHttpClient
    get() {
       return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            })
           .addInterceptor {
               if(kv.decodeString(COOKIE_KEY)==null) {
                   //获取
                   val headers = it.proceed(it.request()).headers("Set-Cookie")
                   logi("Get-Cookie:$headers")
                   kv.encode(COOKIE_KEY,headers.toString())
               }
               //设置
               it.proceed(it.request().newBuilder().addHeader("Cookie", kv.decodeString(COOKIE_KEY)).build())
           }
            .build()
    }

    val service=getService(ApiService::class.java,ApiService.BASE_URL)
    private fun <T> getService(clazz: Class<T>, baseUrl:String):T{
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .build()
            .create(clazz)

    }
}
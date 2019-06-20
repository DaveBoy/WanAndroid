package com.daveboy.wanandroid.util

import android.util.Log
import com.daveboy.wanandroid.config.Config

fun logi(msg:String){
    if(Config.loggable){
        Log.i("WanAndroidKotlin",msg)
    }
}
package com.daveboy.wanandroid.http

data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)
